package com.dh.PI.services;

import com.dh.PI.dto.bookingDTO.BookingRequestDTO;
import com.dh.PI.dto.bookingDTO.BookingResponseDTO;
import com.dh.PI.exceptions.LimitExceededException;
import com.dh.PI.exceptions.NoHaveBookingsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Booking;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import com.dh.PI.repositories.BookingRepository;
import com.dh.PI.repositories.ProductRepository;
import com.dh.PI.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public BookingResponseDTO create(BookingRequestDTO bookingRequestDTO){

        User user = userRepository.findByEmail(bookingRequestDTO.getEmail());

        if (user == null){
            throw new ResourceNotFoundException("User not found");
        }

        Optional<Product> product = productRepository.findById(bookingRequestDTO.getProductId());

        if (product.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        if (!repository.carReservations(bookingRequestDTO.getStartDate(), bookingRequestDTO.getEndDate(),
                bookingRequestDTO.getProductId()).isEmpty()){
            throw new LimitExceededException("The car is already booked between these dates");
        }

        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingRequestDTO, booking);

        booking.setUser(user);
        booking.setProduct(product.get());

        return new BookingResponseDTO(repository.save(booking));
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true)
    public BookingResponseDTO findById(Long id) {

        Optional<Booking> booking = repository.findById(id);

        if(booking.isEmpty()){
            throw new ResourceNotFoundException("Booking not found");
        }

        return new BookingResponseDTO(booking.get());
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true)
    public List<BookingResponseDTO> findAllByProduct(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        List<Booking> bookings = repository.findAllByProduct(product.get());

        if(bookings.isEmpty()){
            throw new NoHaveBookingsException("No have bookings for this product");
        }

        return bookings.stream().map(BookingResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true)
    public List<BookingResponseDTO> findAllByUser(Long id){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not registered");
        }

        List<Booking> bookings = repository.findAllByUser(user.get());

        if (bookings.isEmpty()){
            throw new NoHaveBookingsException("No have booking for this user");
        }

        return bookings.stream().map(BookingResponseDTO::new).collect(Collectors.toList());
    }


    public void deleteById(Long id) {

        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Booking not found!");
        }

        repository.deleteById(id);
    }
}
