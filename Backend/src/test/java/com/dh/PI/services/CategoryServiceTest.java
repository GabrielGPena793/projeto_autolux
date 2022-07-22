package com.dh.PI.services;

import com.dh.PI.dto.categoriesDTO.CategoryDTO;
import com.dh.PI.exceptions.ResourceAlreadyExistsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Category;
import com.dh.PI.model.Classification;
import com.dh.PI.model.Product;
import com.dh.PI.repositories.CategoryRepository;
import com.dh.PI.repositories.ProductRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    public static final String QUALIFICATION    = "Coupes";
    public static final String DESCRIPTION      = "coupes teste";
    public static final String IMAGE_URL        = "coupes.png";
    public static final int INDEX               = 0;
    public static final long ID = 1L;

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository repository;
    @Mock
    private ProductRepository productRepository;

    private CategoryDTO categoryDTO;
    private Category category;
    private Optional<Category> categoryOptional;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnCategoryDTOWhenCreateANewCategory() {
        when(repository.existsByQualification(anyString())).thenReturn(false);
        when(repository.save(any())).thenReturn(category);

        CategoryDTO result = categoryService.create(categoryDTO);

        assertNotNull(result);
        assertEquals(CategoryDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(QUALIFICATION, result.getQualification());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(IMAGE_URL, result.getImageUrl());
    }


    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenCreateAExistingCategory() {
        when(repository.existsByQualification(anyString())).thenReturn(true);
        when(repository.save(any())).thenReturn(category);

        try {
            CategoryDTO result = categoryService.create(categoryDTO);
            fail("Should be throw a exception");
        }catch (Exception ex){
            assertEquals(ResourceAlreadyExistsException.class, ex.getClass());
            assertEquals("This category already registered", ex.getMessage());
        }

    }

    @Test
    void shouldReturnListCategoryDTOWhenFindAllCategories() {
        when(repository.findAll()).thenReturn(List.of(category));

        List<CategoryDTO> result = categoryService.findAll();

        assertNotNull(result);
        assertEquals(CategoryDTO.class, result.get(INDEX).getClass());
        assertEquals(ID, result.get(INDEX).getId());
        assertEquals(QUALIFICATION, result.get(INDEX).getQualification());
        assertEquals(DESCRIPTION, result.get(INDEX).getDescription());
        assertEquals(IMAGE_URL, result.get(INDEX).getImageUrl());
    }

    @Test
    void shouldReturnCategoryDTOWhenUpdateACategory() {
        when(repository.findById(anyLong())).thenReturn(categoryOptional);
        when(repository.save(any())).thenReturn(category);
        categoryDTO.setId(ID);

        CategoryDTO result = categoryService.update(categoryDTO);

        assertNotNull(result);
        assertEquals(CategoryDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(QUALIFICATION, result.getQualification());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(IMAGE_URL, result.getImageUrl());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenUpdateANoExistingCategory() {
        when(repository.findById(anyLong())).thenReturn(null);
        when(repository.save(any())).thenReturn(category);

        try {
            CategoryDTO result = categoryService.update(categoryDTO);
            fail("Should be throw a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Category not found for this id", ex.getMessage());
        }

    }

    @Test
    void shouldDeleteWhenPassCategoryId() {
        when(repository.findById(anyLong())).thenReturn(categoryOptional);
        when(productRepository.findAllByCategory(any())).thenReturn(List.of(product));
        when(productRepository.saveAll(List.of(product))).thenReturn(null);
        doNothing().when(repository).delete(any());

        categoryService.delete(ID);

       verify(repository, times(1)).delete(any());
    }

    @Test
    void shouldResourceNotFoundExceptionWhenCategoryIdNoExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(productRepository.findAllByCategory(any())).thenReturn(List.of(product));
        when(productRepository.saveAll(List.of(product))).thenReturn(null);
        doNothing().when(repository).delete(any());

        try {
            categoryService.delete(ID);
            fail("Should be throw a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Category not found for this id", ex.getMessage());
            verify(repository, times(0)).delete(any());
        }
    }

    @Test
    void shouldReturnCategoryWhenFindByQualification() {
        when(repository.findByQualification(any())).thenReturn(category);

        Category result = categoryService.findByQualification(QUALIFICATION);

        assertNotNull(result);
        assertEquals(Category.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(QUALIFICATION, result.getQualification());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(IMAGE_URL, result.getImageUrl());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindByQualificationNoExists() {
        when(repository.findByQualification(any())).thenReturn(null);

        try{
            Category result = categoryService.findByQualification(QUALIFICATION);
            fail("Should be throw a exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Category " + QUALIFICATION + " not registered", ex.getMessage());
        }

    }



    private void startSetup(){
        category = new Category(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        categoryDTO = new CategoryDTO(null, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        categoryOptional = Optional.of(new Category(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL));
        product = new Product(ID, "Produto1", 0.0, 0, "produto novo",
                null, null, List.of(new Classification(ID, ID, ID, 5.0)),
                List.of(), Set.of(), List.of());

    }

}