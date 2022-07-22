package com.dh.PI.services;

import com.dh.PI.dto.bookingDTO.BookingRequestDTO;
import com.dh.PI.dto.bookingDTO.BookingResponseDTO;
import com.dh.PI.exceptions.LimitExceededException;
import com.dh.PI.exceptions.NoHaveBookingsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Booking;
import com.dh.PI.model.Classification;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import com.dh.PI.repositories.BookingRepository;
import com.dh.PI.repositories.ProductRepository;
import com.dh.PI.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    public static final Long ID                     = 1L;
    public static final LocalDateTime START_TIME    = LocalDateTime.now();
    public static final LocalDate START_DATE        = LocalDate.of(2022, 6, 6);
    public static final LocalDate END_DATE          = LocalDate.of(2022, 6, 10);
    public static final int INDEX = 0;
    public static final String EMAIL = "gabriel@hotmail.com";

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    private BookingRequestDTO bookingRequestDTO;
    private Optional<User> optionalUser;
    private Optional<Product> optionalProduct;
    private Booking booking;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void shouldReturnResponseEntityWhenCreateANewBooking() {
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(repository.carReservations(any(), any(), any())).thenReturn(List.of());
        when(repository.save(any())).thenReturn(booking);

        BookingResponseDTO result = bookingService.create(bookingRequestDTO);

        assertNotNull(result);
        assertEquals(BookingResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(String.valueOf(START_DATE), result.getStartDate());
        assertEquals(String.valueOf(END_DATE), result.getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), result.getStartTime());
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(user.getId(), result.getProduct().getId());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenCreteAndUserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(repository.carReservations(any(), any(), any())).thenReturn(List.of(booking));
        when(repository.save(any())).thenReturn(booking);

        try {
            bookingService.create(bookingRequestDTO);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("User not found", ex.getMessage());
        }
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenCreateAndProductNotFound() {
        when(userRepository.findById(any())).thenReturn(optionalUser);
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        when(repository.carReservations(any(), any(), any())).thenReturn(List.of(booking));
        when(repository.save(any())).thenReturn(booking);

        try {
            bookingService.create(bookingRequestDTO);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("User not found", ex.getMessage());
        }
    }

    @Test
    void shouldReturnLimitExceededExceptionWhenCarIsNotAvailableForBooking() {
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(repository.carReservations(any(), any(), any())).thenReturn(List.of(booking));
        when(repository.save(any())).thenReturn(booking);

        try {
            bookingService.create(bookingRequestDTO);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(LimitExceededException.class, ex.getClass());
            assertEquals("The car is already booked between these dates", ex.getMessage());
        }
    }

    @Test
    void shouldReturnResponseEntityWhenFindById() {
        when(repository.findById(any())).thenReturn(Optional.of(booking));

        BookingResponseDTO result = bookingService.findById(ID);

        assertNotNull(result);
        assertEquals(BookingResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(String.valueOf(START_DATE), result.getStartDate());
        assertEquals(String.valueOf(END_DATE), result.getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), result.getStartTime());
        assertEquals(optionalUser.get().getId(), result.getUser().getId());
        assertEquals(optionalProduct.get().getId(), result.getProduct().getId());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindAllByIdAndBookingNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        try {
            bookingService.findById(ID);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Booking not found", ex.getMessage());
        }
    }

    @Test
    void shouldReturnResponseEntityWhenFindAllByProduct() {
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(repository.findAllByProduct(any())).thenReturn(List.of(booking));

        List<BookingResponseDTO> result = bookingService.findAllByProduct(ID);

        assertNotNull(result);
        assertEquals(BookingResponseDTO.class, result.get(INDEX).getClass());
        assertEquals(ID, result.get(INDEX).getId());
        assertEquals(String.valueOf(START_DATE), result.get(INDEX).getStartDate());
        assertEquals(String.valueOf(END_DATE), result.get(INDEX).getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), result.get(INDEX).getStartTime());
        assertEquals(optionalUser.get().getId(), result.get(INDEX).getUser().getId());
        assertEquals(optionalProduct.get().getId(), result.get(INDEX).getProduct().getId());
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindAllByProductAndProductNotFound() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        try {
            bookingService.findAllByProduct(ID);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Product not found", ex.getMessage());
        }
    }

    @Test
    void shouldReturnNoHaveBookingsExceptionWhenFindAllByProductAndNoHaveBookings() {
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(repository.findAllByProduct(any())).thenReturn(List.of());

        try {
            bookingService.findAllByProduct(ID);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(NoHaveBookingsException.class, ex.getClass());
            assertEquals("No have bookings for this product", ex.getMessage());
        }
    }


    @Test
    void shouldReturnResponseEntityWhenFindAllByUser() {
        when(userRepository.findById(any())).thenReturn(optionalUser);
        when(repository.findAllByUser(any())).thenReturn(List.of(booking));

        List<BookingResponseDTO> result = bookingService.findAllByUser(ID);

        assertNotNull(result);
        assertEquals(BookingResponseDTO.class, result.get(INDEX).getClass());
        assertEquals(ID, result.get(INDEX).getId());
        assertEquals(String.valueOf(START_DATE), result.get(INDEX).getStartDate());
        assertEquals(String.valueOf(END_DATE), result.get(INDEX).getEndDate());
        assertEquals(START_TIME.format(DateTimeFormatter.ofPattern("HH:mm:ss")), result.get(INDEX).getStartTime());
        assertEquals(optionalUser.get().getId(), result.get(INDEX).getUser().getId());
        assertEquals(optionalProduct.get().getId(), result.get(INDEX).getProduct().getId());
    }


    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindAllByUserAndUserNotRegistered() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(repository.findAllByUser(any())).thenReturn(List.of(booking));

        try {
            bookingService.findAllByUser(ID);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("User not registered", ex.getMessage());
        }
    }


    @Test
    void shouldReturnResourceNotFoundExceptionWhenFindAllByUserAndNoHaveBookings() {
        when(userRepository.findById(any())).thenReturn(optionalUser);
        when(repository.findAllByUser(any())).thenReturn(List.of());

        try {
            bookingService.findAllByUser(ID);
            fail("Should return a Exception");
        }catch (Exception ex){
            assertEquals(NoHaveBookingsException.class, ex.getClass());
            assertEquals("No have booking for this user", ex.getMessage());
        }
    }

    @Test
    void deleteById() {
        when(repository.findById(any())).thenReturn(Optional.of(booking));

        bookingService.deleteById(ID);

        verify(repository, times(1)).deleteById(ID);

    }

    @Test
    void whenDeleteByIdThrowException() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        try{
            bookingService.deleteById(ID);
            fail("should be return a Exception");
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Booking not found!", ex.getMessage());
        }

    }

    private void startSetup(){
        bookingRequestDTO = new BookingRequestDTO(START_TIME, START_DATE, END_DATE, EMAIL, ID);

        optionalUser = Optional.of(new User(ID, "Gabriel", "Gomes", EMAIL,
                "123", List.of("USERS")));

        user = new User(ID, "Gabriel", "Gomes", EMAIL,
                "123", List.of("USERS"));

        optionalProduct = Optional.of(new Product(ID, "Produto1", 0.0, INDEX, "produto novo",
                null, null, List.of(new Classification(ID, ID, ID, 5.0)),
                List.of(), Set.of(), List.of()));

        booking = new Booking(ID, START_TIME, START_DATE, END_DATE, optionalUser.get(), optionalProduct.get());

    }


}