package com.airline_reservation_system.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateFlightRequestDTO {
    private Long id;
    private String arrivalTo;
    private String departureFrom;
    private String arrivalTime;
    private String departureTime;
    private double fare;
    private String duration;
}
