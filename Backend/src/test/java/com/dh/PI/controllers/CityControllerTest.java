package com.dh.PI.controllers;

import com.dh.PI.dto.CityDTO;
import com.dh.PI.model.City;
import com.dh.PI.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityControllerTest {

    public static final String COUNTRY      = "Brasil";
    public static final long ID             = 1L;
    public static final String CITYNAME     = "Recife";
    public static final int INDEX           = 0;
    public static final double LONGITUDE    = -43.1890741235295;
    public static final double LATITUDE     = -22.96879036165036;

    @InjectMocks
    private CityController controller;

    @Mock
    private CityService cityService;

    private CityDTO cityDTO;
    private CityDTO cityDTOReturn;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnAnCityDTOWhenCreateANewCityData() {
        Mockito.when(cityService.create(cityDTO)).thenReturn(cityDTOReturn);

        ResponseEntity<CityDTO> result = controller.create(cityDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CityDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals(COUNTRY, result.getBody().getCountry());
        assertEquals(CITYNAME, result.getBody().getName());
    }

    @Test
    void shouldReturnAListCityDTOWhenFindAll() {
        Mockito.when(cityService.findAll()).thenReturn(List.of(cityDTOReturn, cityDTOReturn));

        ResponseEntity<List<CityDTO>> result = controller.findAll();

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CityDTO.class, result.getBody().get(INDEX).getClass());
        assertEquals(2, result.getBody().size());

        assertEquals(ID, result.getBody().get(INDEX).getId());
        assertEquals(COUNTRY, result.getBody().get(INDEX).getCountry());
        assertEquals(CITYNAME, result.getBody().get(INDEX).getName());
        assertEquals(LONGITUDE, result.getBody().get(INDEX).getLongitude());
        assertEquals(LATITUDE, result.getBody().get(INDEX).getLatitude());
    }

    @Test
    void shouldReturnAnCityDTOWhenUpdateACity() {
        Mockito.when(cityService.update(cityDTO)).thenReturn(cityDTOReturn);

        ResponseEntity<CityDTO> result = controller.update(cityDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(CityDTO.class, result.getBody().getClass());

        assertEquals(ID, result.getBody().getId());
        assertEquals(COUNTRY, result.getBody().getCountry());
        assertEquals(CITYNAME, result.getBody().getName());
    }


    private void  startSetup(){
        cityDTO = new CityDTO(null, CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        cityDTOReturn = new CityDTO(ID, CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
    }
}