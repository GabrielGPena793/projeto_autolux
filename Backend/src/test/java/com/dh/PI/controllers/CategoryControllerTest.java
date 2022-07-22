package com.dh.PI.controllers;

import com.dh.PI.dto.categoriesDTO.CategoryDTO;
import com.dh.PI.model.Category;
import com.dh.PI.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryControllerTest {

    public static final String QUALIFICATION = "Coupes";
    public static final String DESCRIPTION = "coupes teste";
    public static final String IMAGE_URL = "coupes.png";
    public static final long ID = 1L;
    public static final int INDEX = 0;
    @InjectMocks
    private CategoryController controller;
    @Mock
    private CategoryService service;

    private CategoryDTO categoryDTO;
    private CategoryDTO categoryDTOResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnResponseEntityWhenCreateANewCategory() {
        when(service.create(categoryDTO)).thenReturn(categoryDTOResponse);

        ResponseEntity<CategoryDTO> result = controller.create(categoryDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CategoryDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals(QUALIFICATION, result.getBody().getQualification());
        assertEquals(DESCRIPTION, result.getBody().getDescription());
        assertEquals(IMAGE_URL, result.getBody().getImageUrl());
    }

    @Test
    void shouldReturnListResponseEntityWhenFindAllCategories() {
        when(service.findAll()).thenReturn(List.of(categoryDTOResponse));

        ResponseEntity<List<CategoryDTO>> result = controller.findAll();

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CategoryDTO.class, result.getBody().get(INDEX).getClass());

        assertEquals(ID, result.getBody().get(INDEX).getId());
        assertEquals(QUALIFICATION, result.getBody().get(INDEX).getQualification());
        assertEquals(DESCRIPTION, result.getBody().get(INDEX).getDescription());
        assertEquals(IMAGE_URL, result.getBody().get(INDEX).getImageUrl());
    }

    @Test
    void shouldReturnResponseEntityWhenUpdateACategory() {
        when(service.update(categoryDTO)).thenReturn(categoryDTOResponse);

        ResponseEntity<CategoryDTO> result = controller.update(categoryDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CategoryDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals(QUALIFICATION, result.getBody().getQualification());
        assertEquals(DESCRIPTION, result.getBody().getDescription());
        assertEquals(IMAGE_URL, result.getBody().getImageUrl());
    }

    @Test
    void shouldReturnNoContentWhenDeleteCategory() {
        doNothing().when(service).delete(any());

        ResponseEntity<?> result = controller.delete(ID);

        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());

        verify(service, times(1)).delete(any());
    }


    public void startSetup(){
        categoryDTO = new CategoryDTO(null, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        categoryDTOResponse = new CategoryDTO(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL);
    }
}
