package com.dh.PI.security;

import com.dh.PI.config.SecurityConfig;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JWTFilterTest {

    @InjectMocks
    private JWTFilter jwtFilter;
    @Mock
    private SecurityConfig securityConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private static JWTCreator jwtCreator;

    private JWTObject create;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(securityConfig, "EXPIRATION", 360000L);
        ReflectionTestUtils.setField(securityConfig, "PREFIX", "Bearer");
        ReflectionTestUtils.setField(securityConfig, "KEY", "SECRET_KEY");
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        try(MockedStatic<JWTCreator> result = mockStatic(JWTCreator.class)){
            result.when(() -> JWTCreator.create(any(),any(),anyString()))
                    .thenReturn(create);
        }
        when(request.getHeader(anyString())).thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2NTQ4OTIxNzAsImV" +
                "4cCI6MTY1NDg5NTc3MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSUyJdfQ." +
                "T2WassBTi4_jQhcv3kXmHBNXsPmDxcvnaFxHQne05ll5sdtvt6mwcRDqbJuq77VXpEKL8rAGT-LklaP4OffDaA");

        try {
            jwtFilter.doFilterInternal(request, response, filterChain);
        }catch (Exception ex){
            fail("Throw a error");
        }
    }

    private void StartSetup(){
        create = new JWTObject("usuario", new Date(), new Date(), List.of("USERS"));
    }
}