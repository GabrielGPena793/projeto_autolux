package com.dh.PI.exceptions.handle;

import com.dh.PI.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomizedResponseEntityExceptionHandlerTest {

    @InjectMocks
    private CustomizedResponseEntityExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenResourceNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerAllExceptionsNotFound(
                new ResourceNotFoundException("Object Not Found"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("Object Not Found", response.getBody().getMessage());
        assertEquals(404, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }


    @Test
    void whenResourceAlreadyExistsExceptionThenReturnResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerAllExceptionsExists(
                new ResourceAlreadyExistsException("Object already exists"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("Object already exists", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }


    @Test
    void whenLoginExceptionExceptionThenReturnResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerLoginException(
                new CreateNewAccountException("This is not registered"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("This is not registered", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }

    @Test
    void whenCreateNewAccountExceptionThenReturnResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerCreateNewAccountException(
                new LoginException("Already exists a account for this email"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("Already exists a account for this email", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }

    @Test
    void whenDateExceptionThenReturnResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerEmptyStackException(
                new DateException("Car already booked for this date"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("Car already booked for this date", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }

    @Test
    void whenLimitExceededExceptionResponseEntity() {
        ResponseEntity<ExceptionResponse> response = exceptionHandler.handlerLimitExceededException(
                new LimitExceededException("Vote only 0 - 5 "), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionResponse.class, response.getBody().getClass());
        assertEquals("Vote only 0 - 5 ", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
        assertNotNull( response.getBody().getDetails());
        assertEquals(Date.class, response.getBody().getTimestamp().getClass());
    }

}