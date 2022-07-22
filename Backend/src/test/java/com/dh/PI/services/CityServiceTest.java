package com.dh.PI.services;

import com.dh.PI.dto.CityDTO;
import com.dh.PI.dto.Session;
import com.dh.PI.exceptions.LoginException;
import com.dh.PI.exceptions.ResourceAlreadyExistsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.City;
import com.dh.PI.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityServiceTest {

    public static final String COUNTRY      = "Brasil";
    public static final long ID             = 1L;
    public static final String CITYNAME     = "Recife";
    public static final double LONGITUDE    = -43.1890741235295;
    public static final double LATITUDE     = -22.96879036165036;

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository repository;
    private CityDTO cityDTO;
    private City city;
    private City cityReturn;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnCityDTOWhenCreateAnNewCityData() {
        when(repository.existsByNameAndCountry(anyString(), anyString())).thenReturn(false);
        when(repository.save(city)).thenReturn(cityReturn);

        CityDTO result = cityService.create(cityDTO);

        assertNotNull(result);
        assertEquals(CityDTO.class, result.getClass());
        assertEquals(ID , result.getId());
        assertEquals(COUNTRY , result.getCountry());
        assertEquals(CITYNAME , result.getName());
    }

    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenCreateAnExistsCityData() {
        when(repository.existsByNameAndCountry(anyString(), anyString())).thenReturn(true);

        try {
            CityDTO result = cityService.create(cityDTO);
            fail("should be throw an exception");
        }catch (Exception ex){
            assertEquals(ResourceAlreadyExistsException.class, ex.getClass());
            assertEquals("This city already registered", ex.getMessage());
        }
    }

    @Test
    void shouldReturnAnListOfCitiesWhenFindAllCities() {
        when(repository.findAll()).thenReturn(List.of(cityReturn,cityReturn,cityReturn));

        List<CityDTO> result = cityService.findAll();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(CityDTO.class, result.get(0).getClass());
        assertEquals(CITYNAME, result.get(0).getName());
        assertEquals(COUNTRY , result.get(0).getCountry());
        assertEquals(ID , result.get(0).getId());
    }

    @Test
    void shouldReturnACityWhenFindByName() {
        when(repository.findByName(anyString())).thenReturn(cityReturn);

        City result = cityService.findByName("Recife");

        assertNotNull(result);
        assertEquals(City.class, result.getClass());
        assertEquals(ID , result.getId());
        assertEquals(COUNTRY , result.getCountry());
        assertEquals(CITYNAME , result.getName());
        assertEquals(LATITUDE , result.getLatitude());
        assertEquals(LONGITUDE , result.getLongitude());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindByName() {
        when(repository.findByName(anyString())).thenReturn(null);

        try {
            City result = cityService.findByName("Recife");
            fail("should be throw an exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("City " + "Recife" + " not registered", ex.getMessage());
        }

    }

    @Test
    void shouldReturnCityDTOWhenUpdateACity() {
        when(repository.findByName(anyString())).thenReturn(city);
        when(repository.saveAndFlush(city)).thenReturn(cityReturn);

        CityDTO result = cityService.update(cityDTO);

        assertNotNull(result);
        assertEquals(CityDTO.class, result.getClass());
        assertEquals(ID , result.getId());
        assertEquals(COUNTRY , result.getCountry());
        assertEquals(CITYNAME , result.getName());
    }

    private void  startSetup(){
        cityDTO = new CityDTO(null,  CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        city = new City(null,  CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        cityReturn = new City(ID,  CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
    }
}