package com.airline_reservation_system.Service;

import com.airline_reservation_system.DTO.RequestDTO.SaveFlightRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateFlightRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveFlightResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ShowFlightResponseDTO;
import com.airline_reservation_system.Model.Flight;
import com.airline_reservation_system.Repository.FlightRepository;
import com.airline_reservation_system.Validaton.LocationValidation.ValidateLocation;
import com.airline_reservation_system.Validaton.TimeValidation.ValidateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public SaveFlightResponseDTO saveFlight(SaveFlightRequestDTO requestDTO) {
        try {
            ValidateLocation.validateLocation(requestDTO.getArrivalTo());
            ValidateLocation.validateLocation(requestDTO.getDepartureFrom());
            ValidateTime.validateTime(requestDTO.getArrivalTime());
            ValidateTime.validateTime(requestDTO.getDepartureTime());

            Flight flight = new Flight();
            flight.setFlightName(requestDTO.getName());
            flight.setNoOfSeats(requestDTO.getNoOfSeats());
            flight.setArrivalTo(requestDTO.getArrivalTo());
            flight.setDepartureFrom(requestDTO.getDepartureFrom());
            flight.setArrivalTime(requestDTO.getArrivalTime());
            flight.setDepartureTime(requestDTO.getDepartureTime());
            flight.setFare(requestDTO.getFare());
            flight.setDuration(requestDTO.getDuration());
            flight.setFlightsSoldTickets(new ArrayList<>());
            flight.setCustomerList(new ArrayList<>());

            flightRepository.save(flight);

            return getSaveFlightResponseDTO(flight);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public SaveFlightResponseDTO updateFlight(UpdateFlightRequestDTO requestDTO) {
        try {
            Flight flight = flightRepository.findById(requestDTO.getId()).orElseThrow();

            ValidateLocation.validateLocation(requestDTO.getArrivalTo());
            ValidateLocation.validateLocation(requestDTO.getDepartureFrom());
            ValidateTime.validateTime(requestDTO.getArrivalTime());
            ValidateTime.validateTime(requestDTO.getDepartureTime());

            flight.setArrivalTo(requestDTO.getArrivalTo());
            flight.setDepartureFrom(requestDTO.getDepartureFrom());
            flight.setArrivalTime(requestDTO.getArrivalTime());
            flight.setDepartureTime(requestDTO.getDepartureTime());
            flight.setFare(requestDTO.getFare());
            flight.setDuration(requestDTO.getDuration());

            flightRepository.save(flight);

            return getSaveFlightResponseDTO(flight);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ShowFlightResponseDTO getFlightById(Long id) {
        try {
            Flight flight = flightRepository.findById(id).orElseThrow();

            ShowFlightResponseDTO responseDTO = new ShowFlightResponseDTO();
            responseDTO.setId(flight.getId());
            responseDTO.setFlightName(flight.getFlightName());
            responseDTO.setArrivalTo(flight.getArrivalTo());
            responseDTO.setDepartureFrom(flight.getDepartureFrom());
            responseDTO.setArrivalTime(flight.getArrivalTime());
            responseDTO.setDepartureTime(flight.getDepartureTime());
            responseDTO.setFare(flight.getFare());
            responseDTO.setDuration(flight.getDuration());
            responseDTO.setNoOfSeatsAvailable(flight.getNoOfSeats() - flight.getFlightsSoldTickets().size());

            return responseDTO;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SaveFlightResponseDTO getSaveFlightResponseDTO(Flight flight) {
        SaveFlightResponseDTO responseDTO = new SaveFlightResponseDTO();
        responseDTO.setId(flight.getId());
        responseDTO.setFlightName(flight.getFlightName());
        responseDTO.setNoOfSeats(flight.getNoOfSeats());
        responseDTO.setArrivalTo(flight.getArrivalTo());
        responseDTO.setDepartureFrom(flight.getDepartureFrom());
        responseDTO.setArrivalTime(flight.getArrivalTime());
        responseDTO.setDepartureTime(flight.getDepartureTime());
        responseDTO.setFare(flight.getFare());
        responseDTO.setDuration(flight.getDuration());
        return responseDTO;
    }
}
