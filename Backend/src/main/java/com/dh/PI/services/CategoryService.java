package com.dh.PI.services;

import com.dh.PI.dto.categoriesDTO.CategoryDTO;
import com.dh.PI.exceptions.ResourceAlreadyExistsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Category;
import com.dh.PI.model.Product;
import com.dh.PI.repositories.CategoryRepository;
import com.dh.PI.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ProductRepository productRepository;

    public CategoryDTO create(CategoryDTO categoryDTO){

        if(repository.existsByQualification(categoryDTO.getQualification())){
            throw new ResourceAlreadyExistsException("This category already registered");
        }

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);

        return new CategoryDTO(repository.save(category));
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public CategoryDTO update(CategoryDTO categoryDTO){
        Optional<Category> categoryEntity = repository.findById(categoryDTO.getId());

        if (categoryEntity.isEmpty()){
            throw new ResourceNotFoundException("Category not found for this id");
        }

        BeanUtils.copyProperties(categoryDTO, categoryEntity.get());

        return new CategoryDTO(repository.save(categoryEntity.get()));
    }

    public void delete(Long id){
        Optional<Category> categoryEntity = repository.findById(id);

        if (categoryEntity.isEmpty()){
            throw new ResourceNotFoundException("Category not found for this id");
        }

        List<Product> products = productRepository.findAllByCategory(categoryEntity.get());
        products.forEach(product -> product.setCategory(null));
        productRepository.saveAll(products);

        repository.delete(categoryEntity.get());

    }

    @Transactional(readOnly = true)
    public Category findByQualification(String qualification){

        Category category = repository.findByQualification(qualification);

        if (category == null){
            throw new ResourceNotFoundException("Category " + qualification + " not registered");
        }

       return category;
    }

}
