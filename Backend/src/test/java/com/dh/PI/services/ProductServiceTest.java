package com.dh.PI.services;

import com.dh.PI.dto.ImageDTO;
import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.dto.productsDTO.ProductRequestDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.*;
import com.dh.PI.repositories.CityRepository;
import com.dh.PI.repositories.ProductCharacteristicRepository;
import com.dh.PI.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    public static final Long ID                     = 1L;
    public static final String QUALIFICATION    = "Coupes";
    public static final String DESCRIPTION      = "coupes teste";
    public static final String IMAGE_URL        = "coupes.png";
    public static final String COUNTRY      = "Brasil";
    public static final String CITYNAME     = "Recife";
    public static final double LONGITUDE    = -43.1890741235295;
    public static final double LATITUDE     = -22.96879036165036;
    public static final String NAME         = "Motor";
    public static final String ICON         = "coupes.png";

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository repository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CharacteristicService characteristicService;
    @Mock
    private ProductCharacteristicRepository productCharacteristicRepository;
    @Mock
    private Pageable pageable;


    private ProductRequestDTO productRequestDTO;
    private Product product;
    private Category category;
    private City city;
    private Characteristic characteristic;
    private ProductCharacteristicDTO productCharacteristicDTO;
    private ProductCharacteristic productCharacteristic;
    private Page<Product> productPage;
    private Image image;
    private ImageDTO imageDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void create() {
        when(categoryService.findByQualification(anyString())).thenReturn(category);
        when(cityService.findByName(anyString())).thenReturn(city);
        when(repository.save(any())).thenReturn(product);
        when(characteristicService.findByName(any())).thenReturn(characteristic);
        when(productCharacteristicRepository.save(any())).thenReturn(productCharacteristic);
        when(repository.save(any())).thenReturn(product);
        product.getImages().add(image);

        ProductResponseDTO result = productService.create(productRequestDTO);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals("Produto1", result.getName());
        assertEquals(5.0, result.getScore());
        assertEquals(2, result.getCountScore());
        assertEquals("Produto novo", result.getDescription());
        assertEquals(ID, result.getCategory().getId());
        assertEquals(ID, result.getImages().get(0).getId());
        assertEquals("Motor", result.getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getCharacteristics().get(0).getDescription());
    }

    @Test
    void findAll() {
        when(repository.findAll(pageable)).thenReturn(productPage);
        product.getProductCharacteristics().add(productCharacteristic);
        product.getImages().add(image);

        Page<ProductResponseDTO> result = productService.findAll(pageable);

        assertNotNull(result);
        assertEquals(PageImpl.class, result.getClass());
        assertEquals(ProductResponseDTO.class,  result.get().collect(Collectors.toList()).get(0).getClass());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(2, result.get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void update() {
        when(repository.findById(any())).thenReturn(Optional.of(product));
        when(categoryService.findByQualification(anyString())).thenReturn(category);
        when(cityService.findByName(anyString())).thenReturn(city);
        when(characteristicService.findByName(any())).thenReturn(characteristic);
        when(productCharacteristicRepository.save(any())).thenReturn(productCharacteristic);
        when(repository.save(any())).thenReturn(product);
        product.getImages().add(image);


        ProductResponseDTO result = productService.update(productRequestDTO);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals("Produto1", result.getName());
        assertEquals(5.0, result.getScore());
        assertEquals(2, result.getCountScore());
        assertEquals("Produto novo", result.getDescription());
        assertEquals(ID, result.getCategory().getId());
        assertEquals(ID, result.getImages().get(0).getId());
        assertEquals("Motor", result.getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getCharacteristics().get(0).getDescription());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenProductNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        when(categoryService.findByQualification(anyString())).thenReturn(category);
        when(cityService.findByName(anyString())).thenReturn(city);
        when(characteristicService.findByName(any())).thenReturn(characteristic);
        when(productCharacteristicRepository.save(any())).thenReturn(productCharacteristic);
        when(repository.save(any())).thenReturn(product);

        try{
            productService.update(productRequestDTO);
            fail("Should be return a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Product not found for this id", ex.getMessage());
        }

    }

    @Test
    void addImages() {
        when(repository.findById(any())).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);
        product.getImages().add(image);
        product.getProductCharacteristics().add(productCharacteristic);

        ProductResponseDTO result = productService.addImages(ID, Set.of(imageDTO));

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals("Produto1", result.getName());
        assertEquals(5.0, result.getScore());
        assertEquals(2, result.getCountScore());
        assertEquals("Produto novo", result.getDescription());
        assertEquals(ID, result.getCategory().getId());
        assertEquals(ID, result.getImages().get(0).getId());
        assertEquals("Motor", result.getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getCharacteristics().get(0).getDescription());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenAddImagesInProductNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(product);
        product.getImages().add(image);
        product.getProductCharacteristics().add(productCharacteristic);

        try{
            productService.addImages(ID, Set.of(imageDTO));
            fail("Should be return a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Product not found for this id", ex.getMessage());
        }
    }

    @Test
    void delete() {
        when(repository.findById(any())).thenReturn(Optional.of(product));
        doNothing().when(repository).delete(any());

        productService.delete(ID);

        verify(repository, times(1)).delete(any());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenDeleteAndProductNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(repository).delete(any());

        try{
            productService.delete(ID);
            fail("Should be return a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Product not found for this id", ex.getMessage());
            verify(repository, times(0)).delete(any());
        }
    }

    @Test
    void findById() {
        product.getProductCharacteristics().add(productCharacteristic);
        product.getImages().add(image);
        when(repository.findById(any())).thenReturn(Optional.of(product));

        ProductResponseDTO result = productService.findById(ID);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals("Produto1", result.getName());
        assertEquals(5.0, result.getScore());
        assertEquals(2, result.getCountScore());
        assertEquals("Produto novo", result.getDescription());
        assertEquals(ID, result.getCategory().getId());
        assertEquals(ID, result.getImages().get(0).getId());
        assertEquals("Motor", result.getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getCharacteristics().get(0).getDescription());

    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindByIdAndProductNotExists(){
        when(repository.findById(any())).thenReturn(Optional.empty());

        try{
            productService.findById(ID);
            fail("Should be return a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Product not found for this id", ex.getMessage());
        }

    }

    @Test
    void findAllByCategory() {
        when(repository.findAllByCategoryQualification(anyString(),any())).thenReturn(productPage);
        product.getProductCharacteristics().add(productCharacteristic);
        product.getImages().add(image);

        Page<ProductResponseDTO> result = productService.findAllProductsByCategory(QUALIFICATION ,pageable);

        assertNotNull(result);
        assertEquals(PageImpl.class, result.getClass());
        assertEquals(ProductResponseDTO.class,  result.get().collect(Collectors.toList()).get(0).getClass());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(2, result.get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }


    @Test
    void findAllByCity() {
        when(repository.findAllByCity(any(),any())).thenReturn(productPage);
        when(cityRepository.findById(any())).thenReturn(Optional.of(city));
        product.getProductCharacteristics().add(productCharacteristic);
        product.getImages().add(image);

        Page<ProductResponseDTO> result = productService.findAllProductsByCity(ID ,pageable);

        assertNotNull(result);
        assertEquals(PageImpl.class, result.getClass());
        assertEquals(ProductResponseDTO.class,  result.get().collect(Collectors.toList()).get(0).getClass());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(2, result.get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

   @Test
    void findByNameBetweenDate() {
        product.getImages().add(image);
        product.getProductCharacteristics().add(productCharacteristic);
        when(repository.findByCityNameBetweenStartDateAndEndDate(any(), any(), any())).thenReturn(List.of(product));

        List<ProductResponseDTO> result = productService.findByNameBetweenDate(ID, "2022-05-20", "2022-05-26");

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
        assertEquals("Produto1", result.get(0).getName());
        assertEquals(5.0, result.get(0).getScore());
        assertEquals(2, result.get(0).getCountScore());
        assertEquals("Produto novo", result.get(0).getDescription());
        assertEquals(ID, result.get(0).getCategory().getId());
        assertEquals(ID, result.get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void findAllBetweenDates() {
        product.getImages().add(image);
        product.getProductCharacteristics().add(productCharacteristic);
        when(repository.findAllProductsBetweenStartDateAndEndDate(any(), any())).thenReturn(List.of(product));

        List<ProductResponseDTO> result = productService.findAllBetweenDates("2022-05-20", "2022-05-26");

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
        assertEquals("Produto1", result.get(0).getName());
        assertEquals(5.0, result.get(0).getScore());
        assertEquals(2, result.get(0).getCountScore());
        assertEquals("Produto novo", result.get(0).getDescription());
        assertEquals(ID, result.get(0).getCategory().getId());
        assertEquals(ID, result.get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get(0).getCharacteristics().get(0).getDescription());
    }

    private void startSetup(){
        image = new Image(ID, "image.png");
        imageDTO = new ImageDTO(ID, "image.png");

        productCharacteristicDTO = new ProductCharacteristicDTO(NAME, "Corre muito", "motor.png");
        category = new Category(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        city = new City(null,  CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        characteristic = new Characteristic(1L, NAME, ICON, Set.of());
        productRequestDTO = new ProductRequestDTO(
                ID,
                "Produto1",
                "Produto novo",
                "Coupes",
                "Recife",
                List.of(productCharacteristicDTO),
                List.of(imageDTO )
        );

        product = new Product(
                ID, "Produto1",
                5.0, 2,
                "Produto novo",
                category, city,
                List.of(new Classification(ID, ID, ID, 5.0)),
                new ArrayList<>(),
                new HashSet<>(), List.of()
        );
        productCharacteristic = new ProductCharacteristic(ID, "Corre muito" , product, characteristic);
        productPage = new PageImpl<>(List.of(product));
    }
}