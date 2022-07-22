package com.dh.PI.dto.bookingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class BookingRequestDTO {

    private LocalDateTime startTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String email;
    private Long productId;
}
