package com.dh.PI.controllers;

import com.dh.PI.dto.bookingDTO.BookingRequestDTO;
import com.dh.PI.dto.bookingDTO.BookingResponseDTO;
import com.dh.PI.model.Booking;
import com.dh.PI.model.Classification;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import com.dh.PI.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookingControllerTest {

    public static final Long ID                     = 1L;
    public static final LocalDateTime START_TIME    = LocalDateTime.now();
    public static final LocalDate START_DATE        = LocalDate.of(2022, 6, 6);
    public static final LocalDate END_DATE          = LocalDate.of(2022, 6, 10);
    public static final int INDEX                   = 0;
    public static final String EMAIL = "gabriel@hotmail.com";

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingService service;

    private BookingRequestDTO bookingRequestDTO;
    private BookingResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void whenCreateReturnResponseEntitySuccess() {
        when(service.create(any())).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> response = bookingController.create(bookingRequestDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BookingResponseDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(String.valueOf(START_DATE), response.getBody().getStartDate());
        assertEquals(String.valueOf(END_DATE), response.getBody().getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), response.getBody().getStartTime());
        assertEquals(ID, response.getBody().getUser().getId());
        assertEquals(ID, response.getBody().getProduct().getId());
    }

    @Test
    void whenFindByIdReturnResponseEntitySuccess() {
        when(service.findById(any())).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> response = bookingController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BookingResponseDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(String.valueOf(START_DATE), response.getBody().getStartDate());
        assertEquals(String.valueOf(END_DATE), response.getBody().getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), response.getBody().getStartTime());
        assertEquals(ID, response.getBody().getUser().getId());
        assertEquals(ID, response.getBody().getProduct().getId());
    }

    @Test
    void whenFindAllProductIdReturnResponseEntitySuccess() {
        when(service.findAllByProduct(any())).thenReturn(List.of(responseDTO));

        ResponseEntity<List<BookingResponseDTO>> response = bookingController.findAllByProduct(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BookingResponseDTO.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(String.valueOf(START_DATE), response.getBody().get(0).getStartDate());
        assertEquals(String.valueOf(END_DATE), response.getBody().get(0).getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), response.getBody().get(0).getStartTime());
        assertEquals(ID, response.getBody().get(0).getUser().getId());
        assertEquals(ID, response.getBody().get(0).getProduct().getId());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void whenFindAllByUserReturnResponseEntitySuccess() {
        when(service.findAllByUser(any())).thenReturn(List.of(responseDTO));

        ResponseEntity<List<BookingResponseDTO>> response = bookingController.findAllByUser(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BookingResponseDTO.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(String.valueOf(START_DATE), response.getBody().get(0).getStartDate());
        assertEquals(String.valueOf(END_DATE), response.getBody().get(0).getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), response.getBody().get(0).getStartTime());
        assertEquals(ID, response.getBody().get(0).getUser().getId());
        assertEquals(ID, response.getBody().get(0).getProduct().getId());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void deleteById() {
        doNothing().when(service).deleteById(any());

        ResponseEntity<?> response = bookingController.deleteById(ID);

        assertNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startSetup() {
        bookingRequestDTO = new BookingRequestDTO(START_TIME, START_DATE, END_DATE, EMAIL, ID);

        User user = new User(ID, "Gabriel", "Gomes", EMAIL,
                "123", List.of("USERS"));

        Product  product = new Product(ID, "Produto1", 0.0, INDEX, "produto novo",
                null, null, List.of(new Classification(ID, ID, ID, 5.0)),
                List.of(), Set.of(), List.of());

        Booking booking = new Booking(ID, START_TIME, START_DATE, END_DATE, user, product);

        responseDTO = new BookingResponseDTO(booking);
    }


}