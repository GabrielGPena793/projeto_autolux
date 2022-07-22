package com.dh.PI.services;

import com.dh.PI.config.SecurityConfig;
import com.dh.PI.dto.Login;
import com.dh.PI.dto.Session;
import com.dh.PI.exceptions.LoginException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.User;
import com.dh.PI.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private SecurityConfig securityConfig;
    @Mock
    private UserRepository repository;

    private Login login;
    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(securityConfig, "EXPIRATION", 360000L);
        ReflectionTestUtils.setField(securityConfig, "PREFIX", "Bearer");
        ReflectionTestUtils.setField(securityConfig, "KEY", "SECRET_KEY");
        setupStart();
    }

    @Test
    void whenLoginReturnSuccess() {
        when(repository.findByEmail(any())).thenReturn(user);
        when(encoder.matches(any(), any())).thenReturn(true);

        Session result = loginService.login(login);

        assertNotNull(result);
        assertEquals(Session.class, result.getClass());
        assertEquals(user.getName() + " " + user.getLastname(), result.getLogin());
        assertNotNull(result.getToken());
        assertEquals(String.class , result.getToken().getClass());
    }



    @Test
    void whenLoginThrowLoginException(){
        when(repository.findByEmail(any())).thenReturn(user);
        when(encoder.matches(any(), any())).thenReturn(false);

        try {
            Session result = loginService.login(login);
            fail("should be throw an exception");
        }catch (Exception ex){
            assertEquals(LoginException.class, ex.getClass());
            assertEquals("Senha inválida, tente novamente!", ex.getMessage());
        }

    }

    @Test
    void whenLoginThrowResourceNotFoundException(){
        when(repository.findByEmail(any())).thenReturn(null);

        try {
            Session result = loginService.login(login);
            fail("should be throw an exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Não existe uma conta cadastrada com esse email", ex.getMessage());
        }

    }

    public void setupStart(){
        login = new Login("gabriel@hotmail.com", "123");
        user = new User(1L, "Gabriel", "Gomes", "gabriel@hotmail.com", "123", List.of("USERS"));
    }
}