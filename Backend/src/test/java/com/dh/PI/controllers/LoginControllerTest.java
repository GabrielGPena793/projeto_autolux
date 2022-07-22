package com.dh.PI.controllers;

import com.dh.PI.dto.Login;
import com.dh.PI.dto.Session;
import com.dh.PI.model.User;
import com.dh.PI.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    public static final String EMAIL = "gabriel@hotmail.com";
    public static final String LOGIN = "Gabriel Gomes";
    public static final String PASSWORD = "123";
    @InjectMocks
    private LoginController loginController;
    @Mock
    private LoginService loginService;

    private Login login;
    private Session session;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setupStart();
    }

    @Test
    void whenRequestLoginReturnSuccess() {
        Mockito.when(loginService.login(Mockito.any())).thenReturn(session);

        ResponseEntity<Session> response = loginController.login(login);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Session.class, response.getBody().getClass());
        assertEquals(LOGIN, response.getBody().getLogin());
        assertEquals(PASSWORD, response.getBody().getToken());

    }

    public void setupStart(){
        login = new Login(EMAIL, PASSWORD);
        session = new Session(1L, List.of("USERS") ,LOGIN, PASSWORD, EMAIL);
    }
}