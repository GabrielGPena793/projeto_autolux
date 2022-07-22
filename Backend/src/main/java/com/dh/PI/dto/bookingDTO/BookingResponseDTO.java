package com.dh.PI.dto.bookingDTO;

import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.dto.userDTO.UserResponseDTO;
import com.dh.PI.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class BookingResponseDTO {

    private Long id;
    private String startTime;
    private String startDate;
    private String endDate;
    private UserResponseDTO user;
    private ProductResponseDTO product;

    public BookingResponseDTO(Booking booking) {
        this.id = booking.getId();
        this.startTime = booking.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.startDate = booking.getStartDate().toString();
        this.endDate = booking.getEndDate().toString();
        this.user = new UserResponseDTO(booking.getUser());
        this.product = new ProductResponseDTO(booking.getProduct());
    }
}
