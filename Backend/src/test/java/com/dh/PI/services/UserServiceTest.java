package com.dh.PI.services;

import com.dh.PI.dto.userDTO.UserRequestDTO;
import com.dh.PI.dto.userDTO.UserResponseDTO;
import com.dh.PI.exceptions.CreateNewAccountException;
import com.dh.PI.model.User;
import com.dh.PI.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    public static final String NAME     = "Gabriel";
    public static final String LASTNAME = "Gomes";
    public static final String EMAIL    = "gabriel@hotmail.com";
    public static final long ID         = 1L;
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void WhenCreateUserReturnSuccess() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(UserResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(NAME , result.getName());
        assertEquals(LASTNAME , result.getLastname());
        assertEquals(EMAIL , result.getEmail());
    }


    @Test
    void WhenCreateUserWithExistingEmailThenThrowCreateNewAccountException() {
        when(userRepository.existsByEmail(any())).thenReturn(true);

        try{
            userService.createUser(userRequestDTO);
        }catch (Exception ex){
            assertEquals(CreateNewAccountException.class, ex.getClass());
            assertEquals("Já existe uma conta com esse email", ex.getMessage());
        }


    }


    private void startSetup(){
        userRequestDTO = new UserRequestDTO(NAME, LASTNAME, EMAIL, "123");
        userResponseDTO = new UserResponseDTO(ID, NAME, LASTNAME, EMAIL);
        user = new User(ID, NAME, LASTNAME, EMAIL,
                passwordEncoder.encode("123"), List.of("USERS"));
    }
}