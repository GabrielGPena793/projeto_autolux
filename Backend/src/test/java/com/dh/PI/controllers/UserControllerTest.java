package com.dh.PI.controllers;

import com.dh.PI.dto.userDTO.UserRequestDTO;
import com.dh.PI.dto.userDTO.UserResponseDTO;
import com.dh.PI.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    public static final String NAME     = "Gabriel";
    public static final String LASTNAME = "Gomes";
    public static final String EMAIL    = "gabriel@hotmail.com";
    public static final long ID         = 1L;

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void whenPostUserReturnSuccessCreated() {
        when(userService.createUser(userRequestDTO)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> result = userController.postUser(userRequestDTO);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(UserResponseDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(ID, result.getBody().getId());
        assertEquals(NAME, result.getBody().getName());
        assertEquals(EMAIL, result.getBody().getEmail());
        assertEquals(LASTNAME, result.getBody().getLastname());
    }

    private void startSetup(){
        userRequestDTO = new UserRequestDTO(NAME, LASTNAME, EMAIL, "123");
        userResponseDTO = new UserResponseDTO(ID, NAME, LASTNAME, EMAIL);

    }
}