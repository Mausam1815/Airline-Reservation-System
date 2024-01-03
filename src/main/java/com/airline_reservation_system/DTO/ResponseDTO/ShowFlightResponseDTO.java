package com.airline_reservation_system.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowFlightResponseDTO {
    private Long id;
    private String flightName;
    private int noOfSeatsAvailable;
    private String arrivalTo;
    private String departureFrom;
    private String arrivalTime;
    private String departureTime;
    private double fare;
    private String duration;
}
