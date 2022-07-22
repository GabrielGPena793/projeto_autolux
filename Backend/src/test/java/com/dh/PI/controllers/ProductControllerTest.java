package com.dh.PI.controllers;

import com.dh.PI.dto.ImageDTO;
import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.dto.ScoreDTO;
import com.dh.PI.dto.productsDTO.ProductRequestDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.model.Category;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import com.dh.PI.services.ClassificationService;
import com.dh.PI.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    public static final Long ID                     = 1L;
    public static final String QUALIFICATION    = "Coupes";
    public static final String DESCRIPTION      = "coupes teste";
    public static final String IMAGE_URL        = "coupes.png";
    public static final String COUNTRY          = "Brasil";
    public static final String CITYNAME         = "Recife";
    public static final double LONGITUDE        = -43.1890741235295;
    public static final double LATITUDE         = -22.96879036165036;
    public static final String NAME             = "Motor";
    public static final String ICON             = "coupes.png";

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService service;
    @Mock
    private ClassificationService classificationService;
    @Mock
    private Pageable pageable;

    private ProductRequestDTO productRequestDTO;
    private ProductResponseDTO productResponseDTO;
    private ProductCharacteristicDTO productCharacteristicDTO;
    private Page<ProductResponseDTO> productResponseDTOPage;
    private ImageDTO imageDTO;
    private ScoreDTO scoreDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void create() {
        when(service.create(any())).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> result = productController.create(productRequestDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals( "Produto1", result.getBody().getName());
        assertEquals( 5.0, result.getBody().getScore());
        assertEquals( 5, result.getBody().getCountScore());
        assertEquals("Produto novo", result.getBody().getDescription());
        assertEquals(ID, result.getBody().getCategory().getId());
        assertEquals(ID, result.getBody().getCity().getId());
        assertEquals(ID, result.getBody().getImages().get(0).getId());
        assertEquals(NAME, result.getBody().getCharacteristics().get(0).getName());
    }

    @Test
    void findAll() {
        when(service.findAll(any())).thenReturn(productResponseDTOPage);

        ResponseEntity<Page<ProductResponseDTO>> result = productController.findAll(pageable);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().get().collect(Collectors.toList()).get(0).getClass());

        assertEquals(PageImpl.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.getBody().get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.getBody().get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(5, result.getBody().get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.getBody().get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.getBody().get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void findById() {
        when(service.findById(any())).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> result = productController.findById(ID);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals( "Produto1", result.getBody().getName());
        assertEquals( 5.0, result.getBody().getScore());
        assertEquals( 5, result.getBody().getCountScore());
        assertEquals("Produto novo", result.getBody().getDescription());
        assertEquals(ID, result.getBody().getCategory().getId());
        assertEquals(ID, result.getBody().getCity().getId());
        assertEquals(ID, result.getBody().getImages().get(0).getId());
        assertEquals(NAME, result.getBody().getCharacteristics().get(0).getName());
    }

    @Test
    void update() {
        when(service.update(any())).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> result = productController.update(productRequestDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals( "Produto1", result.getBody().getName());
        assertEquals( 5.0, result.getBody().getScore());
        assertEquals( 5, result.getBody().getCountScore());
        assertEquals("Produto novo", result.getBody().getDescription());
        assertEquals(ID, result.getBody().getCategory().getId());
        assertEquals(ID, result.getBody().getCity().getId());
        assertEquals(ID, result.getBody().getImages().get(0).getId());
        assertEquals(NAME, result.getBody().getCharacteristics().get(0).getName());
    }

    @Test
    void delete() {
        doNothing().when(service).delete(ID);

        ResponseEntity<?> result = productController.delete(ID);

        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        verify(service, times(1)).delete(ID);
    }

    @Test
    void addImage() {
        when(service.addImages(any(), any())).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> result = productController.addImages(ID, Set.of(imageDTO));

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals( "Produto1", result.getBody().getName());
        assertEquals( 5.0, result.getBody().getScore());
        assertEquals( 5, result.getBody().getCountScore());
        assertEquals("Produto novo", result.getBody().getDescription());
        assertEquals(ID, result.getBody().getCategory().getId());
        assertEquals(ID, result.getBody().getCity().getId());
        assertEquals(ID, result.getBody().getImages().get(0).getId());
        assertEquals(NAME, result.getBody().getCharacteristics().get(0).getName());
    }

    @Test
    void findAllByCategory() {
        when(service.findAllProductsByCategory(any(), any())).thenReturn(productResponseDTOPage);

        ResponseEntity<Page<ProductResponseDTO>> result = productController.findAllProductsByCategory(QUALIFICATION, pageable);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().get().collect(Collectors.toList()).get(0).getClass());

        assertEquals(PageImpl.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.getBody().get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.getBody().get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(5, result.getBody().get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.getBody().get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.getBody().get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void findAllByCity() {
        when(service.findAllProductsByCity(any(), any())).thenReturn(productResponseDTOPage);

        ResponseEntity<Page<ProductResponseDTO>> result = productController.findAllProductsByCity(ID, pageable);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().get().collect(Collectors.toList()).get(0).getClass());

        assertEquals(PageImpl.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.getBody().get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.getBody().get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(5, result.getBody().get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("Produto novo", result.getBody().get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.getBody().get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.getBody().get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getBody().get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void findByNameBetweenDate() {
        when(service.findByNameBetweenDate(any(), any(), any())).thenReturn(List.of(productResponseDTO));

        ResponseEntity<List<ProductResponseDTO>> result = productController
                .findByNameBetweenDate(ID, "2022-03-20","2022-03-28");

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().get(0).getClass());

        assertEquals(ID, result.getBody().get(0).getId());
        assertEquals("Produto1", result.getBody().get(0).getName());
        assertEquals(5.0, result.getBody().get(0).getScore());
        assertEquals(5, result.getBody().get(0).getCountScore());
        assertEquals("Produto novo", result.getBody().get(0).getDescription());
        assertEquals(ID,result.getBody().get(0).getCategory().getId());
        assertEquals(ID, result.getBody().get(0).getImages().get(0).getId());
        assertEquals("Motor", result.getBody().get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getBody().get(0).getCharacteristics().get(0).getDescription());
    }


    @Test
    void productScore() {
        when(classificationService.saveScore(any())).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> result = productController.productScore(scoreDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals( "Produto1", result.getBody().getName());
        assertEquals( 5.0, result.getBody().getScore());
        assertEquals( 5, result.getBody().getCountScore());
        assertEquals("Produto novo", result.getBody().getDescription());
        assertEquals(ID, result.getBody().getCategory().getId());
        assertEquals(ID, result.getBody().getCity().getId());
        assertEquals(ID, result.getBody().getImages().get(0).getId());
        assertEquals(NAME, result.getBody().getCharacteristics().get(0).getName());
    }

    @Test
    void findAllBetweenDates() {
        when(service.findAllBetweenDates(any(), any())).thenReturn(List.of(productResponseDTO));

        ResponseEntity<List<ProductResponseDTO>> result = productController
                .findAllBetweenDates("2022-03-20","2022-03-28");

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(ProductResponseDTO.class, result.getBody().get(0).getClass());

        assertEquals(ID, result.getBody().get(0).getId());
        assertEquals("Produto1", result.getBody().get(0).getName());
        assertEquals(5.0, result.getBody().get(0).getScore());
        assertEquals(5, result.getBody().get(0).getCountScore());
        assertEquals("Produto novo", result.getBody().get(0).getDescription());
        assertEquals(ID,result.getBody().get(0).getCategory().getId());
        assertEquals(ID, result.getBody().get(0).getImages().get(0).getId());
        assertEquals("Motor", result.getBody().get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getBody().get(0).getCharacteristics().get(0).getDescription());
    }

    private void startSetup(){
        productCharacteristicDTO = new ProductCharacteristicDTO(NAME, "Corre muito", "motor.png");
        Category category = new Category(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        City city = new City(ID, CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        imageDTO = new ImageDTO(ID, "image.png");
        productResponseDTO = new ProductResponseDTO(ID, "Produto1",
                5.0, 5, "Produto novo", category,
                city, List.of(imageDTO),List.of(productCharacteristicDTO));
        productRequestDTO = new ProductRequestDTO(
                1L,
                "Produto1",
                "Produto novo",
                "Coupes",
                "Recife",
                List.of(productCharacteristicDTO),
                List.of(new ImageDTO())
        );
        productResponseDTOPage = new PageImpl<>(List.of(productResponseDTO));
        scoreDTO = new ScoreDTO("gabriel@hotmail.com", 1L, 5.0);
    }


}