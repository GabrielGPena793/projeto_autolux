package com.dh.PI.services;

import com.dh.PI.dto.ScoreDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.exceptions.LimitExceededException;
import com.dh.PI.exceptions.LoginException;
import com.dh.PI.model.Classification;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import com.dh.PI.repositories.ClassificationRepository;
import com.dh.PI.repositories.ProductRepository;
import com.dh.PI.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClassificationServiceTest {

    @InjectMocks
    private ClassificationService classificationService;

    @Mock
    private ClassificationRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    private ScoreDTO scoreDTO;
    private User user;
    private Optional<Product> productOptional;
    private Product product;
    private Classification classification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnAnProductWithScoreUpdatedWhenSaveScoreForSameUserAndProduct() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(repository.findByUserIdAndProductId(any(), any())).thenReturn(classification);
        when(productRepository.save(any())).thenReturn(product);

        ProductResponseDTO result = classificationService.saveScore(scoreDTO);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(1, result.getCountScore());
        assertEquals(5.0, result.getScore());
    }

    @Test
    void shouldReturnAnProductWithNewScoreWhenSaveNewScore() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(repository.findByUserIdAndProductId(any(), any())).thenReturn(null);
        when(productRepository.save(any())).thenReturn(product);

        ProductResponseDTO result = classificationService.saveScore(scoreDTO);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(1, result.getCountScore());
        assertEquals(5.0, result.getScore());
    }

    @Test
    void shouldReturnLoginExceptionWhenSaveNewScoreWithEmailInvalid() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(repository.findByUserIdAndProductId(any(), any())).thenReturn(classification);
        when(productRepository.save(any())).thenReturn(product);

        try {
            classificationService.saveScore(scoreDTO);
            fail("Should be throw a Exception");
        }catch (Exception ex){
            assertEquals(LoginException.class, ex.getClass());
            assertEquals("You need to login to give a score", ex.getMessage());
        }

    }

    @Test
    void shouldReturnLimitExceededExceptionWhenSaveNewScoreGreaterThan5() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(repository.findByUserIdAndProductId(any(), any())).thenReturn(classification);
        when(productRepository.save(any())).thenReturn(product);

        scoreDTO.setScore(6.0);
        try {
            classificationService.saveScore(scoreDTO);
            fail("Should be throw a Exception");
        }catch (Exception ex){
            assertEquals(LimitExceededException.class, ex.getClass());
            assertEquals("The number must be between 0 and 5", ex.getMessage());
        }

    }

    @Test
    void shouldReturnLimitExceededExceptionWhenSaveNewScoreGreaterLessThen0() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(repository.findByUserIdAndProductId(any(), any())).thenReturn(classification);
        when(productRepository.save(any())).thenReturn(product);

        scoreDTO.setScore(-1.0);
        try {
            classificationService.saveScore(scoreDTO);
            fail("Should be throw a Exception");
        }catch (Exception ex){
            assertEquals(LimitExceededException.class, ex.getClass());
            assertEquals("The number must be between 0 and 5", ex.getMessage());
        }

    }


    private void startSetup(){
        scoreDTO = new ScoreDTO("gabriel@hotmail.com", 1L, 2.0);
        user = new User(1L, "Gabriel", "Gomes", "gabriel@hotmail.com",
                "1d32sa1das51d35as1", List.of("USERS"));

        productOptional = Optional.of(new Product(1L, "Produto1", 0.0, 0, "produto novo",
                null, null, List.of(new Classification(1L, 1L , 1L , 5.0)),
                List.of(), Set.of(), List.of()));

        product = new Product(1L, "Produto1", 5.0, 1, "produto novo",
                null, null, List.of(), List.of(), Set.of(), List.of());

        classification = new Classification(1L, 1L , 1L , 5.0);
    }

}