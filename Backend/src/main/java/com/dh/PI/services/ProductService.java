package com.dh.PI.services;

import com.dh.PI.dto.ImageDTO;
import com.dh.PI.dto.productsDTO.ProductRequestDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.City;
import com.dh.PI.model.Image;
import com.dh.PI.model.Product;
import com.dh.PI.model.ProductCharacteristic;
import com.dh.PI.repositories.BookingRepository;
import com.dh.PI.repositories.CityRepository;
import com.dh.PI.repositories.ProductCharacteristicRepository;
import com.dh.PI.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final String PRODUCT_NOT_FOUND_FOR_THIS_ID = "Product not found for this id";
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CharacteristicService characteristicService;
    @Autowired
    private ProductCharacteristicRepository productCharacteristicRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO){

        Product product = new Product();

        BeanUtils.copyProperties(productRequestDTO, product);
        product.setCategory(categoryService.findByQualification(productRequestDTO.getCategory()));
        product.setCity(cityService.findByName(productRequestDTO.getCity()));
        product.setImages(productRequestDTO.getImageDTOS().stream().map(Image::new).collect(Collectors.toList()));

        Product productModel = repository.save(product);

        productRequestDTO.getCharacteristics().forEach(chars -> {
            ProductCharacteristic productCharacteristic = new ProductCharacteristic(chars.getDescription(),
                    productModel, characteristicService.findByName(chars.getName()));
            productModel.getProductCharacteristics().add(productCharacteristicRepository.save(productCharacteristic));
        });

        return new ProductResponseDTO(repository.save(productModel));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(ProductResponseDTO::new);
    }

    @Transactional
    public ProductResponseDTO update(ProductRequestDTO productRequestDTO){
        Optional<Product> productEntity = repository.findById(productRequestDTO.getId());

        if (productEntity.isEmpty()){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_FOR_THIS_ID);
        }

        BeanUtils.copyProperties(productRequestDTO, productEntity.get());

        productEntity.get().setCategory(categoryService.findByQualification(productRequestDTO.getCategory()));
        productEntity.get().setCity(cityService.findByName(productRequestDTO.getCity()));
        productEntity.get().setImages(productRequestDTO.getImageDTOS().stream().map(Image::new).collect(Collectors.toList()));

        Product productModel = repository.save(productEntity.get());

        productCharacteristicRepository.dropProductCharacteristicFromOneProduct(productEntity.get().getId());

        productRequestDTO.getCharacteristics().forEach(chars -> {
            ProductCharacteristic productCharacteristic = new ProductCharacteristic(chars.getDescription(),
                    productModel, characteristicService.findByName(chars.getName()));
            productModel.getProductCharacteristics().add(productCharacteristicRepository.save(productCharacteristic));
        });

        return new ProductResponseDTO(repository.save(productModel));
    }

    @Transactional
    public ProductResponseDTO addImages(Long id, Set<ImageDTO> images){
        Optional<Product> productEntity = repository.findById(id);

        if (productEntity.isEmpty()){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_FOR_THIS_ID);
        }

        images.forEach(imageDTO -> {
            Image image = new Image();
            BeanUtils.copyProperties(imageDTO, image);
            productEntity.get().getImages().add(image);
        });

        return new ProductResponseDTO(repository.save(productEntity.get()));
    }

    @Transactional
    public void delete(Long id){
        Optional<Product> productEntity = repository.findById(id);

        if (productEntity.isEmpty()){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_FOR_THIS_ID);
        }

        repository.delete(productEntity.get());
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Optional<Product> productEntity = repository.findById(id);

        if (productEntity.isEmpty()){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_FOR_THIS_ID);
        }

        return new ProductResponseDTO(productEntity.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllProductsByCategory(String qualification, Pageable pageable){
        return repository.findAllByCategoryQualification(qualification, pageable)
                .map(ProductResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllProductsByCity(Long id, Pageable pageable) {

        Optional<City> city = cityRepository.findById(id);

        return repository.findAllByCity(city.get(), pageable).map(ProductResponseDTO::new);
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true)
    public List<ProductResponseDTO> findByNameBetweenDate(Long cityId, String init, String end){

        LocalDate initial = LocalDate.parse(init);
        LocalDate ending = LocalDate.parse(end);

        List<Product> products = repository.findByCityNameBetweenStartDateAndEndDate(initial, ending, cityId);

        return products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAllBetweenDates(String init, String end) {

        LocalDate initial = LocalDate.parse(init);
        LocalDate ending = LocalDate.parse(end);

        List<Product> products = repository.findAllProductsBetweenStartDateAndEndDate(initial, ending);

        return products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }
}
