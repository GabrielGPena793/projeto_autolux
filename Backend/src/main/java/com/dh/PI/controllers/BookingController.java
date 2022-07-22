package com.dh.PI.controllers;

import com.dh.PI.dto.bookingDTO.BookingRequestDTO;
import com.dh.PI.dto.bookingDTO.BookingResponseDTO;
import com.dh.PI.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@RequestBody BookingRequestDTO bookingRequestDTO){
        return ResponseEntity.status(201).body(service.create(bookingRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<BookingResponseDTO>> findAllByProduct(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findAllByProduct(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingResponseDTO>> findAllByUser(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findAllByUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
