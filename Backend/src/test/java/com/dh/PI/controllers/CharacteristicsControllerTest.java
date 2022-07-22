package com.dh.PI.controllers;

import com.dh.PI.dto.characteristics.CharacteristicsDTO;
import com.dh.PI.services.CharacteristicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CharacteristicsControllerTest {

    //variables
    public static final String NAME = "Coupes";
    public static final String ICON = "Icon.png";

    //Preparing scenario for tests
    @InjectMocks
    private CharacteristicsController characteristicsController;
    @Mock
    private CharacteristicService service;

    private CharacteristicsDTO characteristicsDTO;
    private CharacteristicsDTO characteristicsDTOWithId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void create() {
        when(service.create(any())).thenReturn(characteristicsDTOWithId);

        ResponseEntity<CharacteristicsDTO> result = characteristicsController.create(characteristicsDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CharacteristicsDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        assertEquals(1L, result.getBody().getId());
        assertEquals(NAME, result.getBody().getName());
        assertEquals(ICON, result.getBody().getIcon());
    }

    private void startSetup(){
        characteristicsDTO = new CharacteristicsDTO(null, NAME, ICON);
        characteristicsDTOWithId = new CharacteristicsDTO(1L, NAME, ICON);
    }
}