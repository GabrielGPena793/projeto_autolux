package com.dh.PI.services;

import com.dh.PI.dto.characteristics.CharacteristicsDTO;
import com.dh.PI.exceptions.ResourceAlreadyExistsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Characteristic;
import com.dh.PI.repositories.CharacteristicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;

@SpringBootTest
class CharacteristicServiceTest {

    public static final String NAME = "Coupes";
    public static final String ICON = "coupes.png";
    public static final int INDEX = 0;
    @InjectMocks
    private CharacteristicService characteristicService;

    @Mock
    private CharacteristicRepository repository;

    private CharacteristicsDTO characteristicsDTO;
    private Characteristic characteristic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnAnCharacteristicsDTOWhenCreateSuccess() {
        when(repository.findByName(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(characteristic);

        CharacteristicsDTO result = characteristicService.create(characteristicsDTO);

        assertNotNull(result);
        assertEquals(CharacteristicsDTO.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals(NAME, result.getName());
        assertEquals(ICON, result.getIcon());
    }

    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenTryCreateAExistsCharacteristic() {
        when(repository.findByName(any())).thenReturn(characteristic);
        when(repository.save(any())).thenReturn(characteristic);

        try {
            CharacteristicsDTO result = characteristicService.create(characteristicsDTO);
            fail("Should return a Exception");
        }catch (Exception e){
            assertEquals(ResourceAlreadyExistsException.class, e.getClass());
            assertEquals("Characteristic already exist: " + characteristicsDTO.getName(), e.getMessage());
        }
    }

    @Test
    void shouldReturnListCharacteristicWhenFindAllByName() {
        Set<String> list = Set.of(NAME, "Ferrari", "Lambo");
        when(repository.findByName(anyString())).thenReturn(characteristic);

        List<Characteristic> result = characteristicService.findAllByName(list);

        assertNotNull(result);
        assertEquals(Characteristic.class, result.get(INDEX).getClass());
        assertEquals(3, result.size());
        verify(repository, times(3)).findByName(anyString());
        assertEquals(1L, result.get(INDEX).getId());
        assertEquals(NAME, result.get(INDEX).getName());
        assertEquals(ICON, result.get(INDEX).getIcon());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindAllByNameWithNoExistsAny() {
        Set<String> list = Set.of(NAME, "Ferrari", "Lambo");
        when(repository.findByName(anyString())).thenReturn(null);

        try {
            characteristicService.findAllByName(list);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Characteristic not found: " + list, ex.getMessage());
        }
    }


    @Test
    void shouldReturnAnCharacteristicWhenFindAByName() {
        when(repository.findByName(anyString())).thenReturn(characteristic);

        Characteristic result = characteristicService.findByName(NAME);

        assertNotNull(result);
        assertEquals(Characteristic.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals(NAME, result.getName());
        assertEquals(ICON, result.getIcon());
    }

    @Test
    void shouldResourceNotFoundExceptionWhenFindAByNameThatDoesNotExist() {
        when(repository.findByName(anyString())).thenReturn(null);

        try {
            Characteristic result = characteristicService.findByName(NAME);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Characteristic not found: " + NAME, ex.getMessage());
        }

    }

    private void startSetup(){
        characteristicsDTO = new CharacteristicsDTO(null, NAME, ICON);
        characteristic = new Characteristic(1L, NAME, ICON, Set.of());
    }
}