package com.airline_reservation_system.Controller;

import com.airline_reservation_system.DTO.RequestDTO.SaveFlightRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ExceptionResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveFlightResponseDTO;
import com.airline_reservation_system.Service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Operation(summary = "Adds a new flight to database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Flight added successfully!!", content = {@Content(schema = @Schema(implementation = SaveFlightResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application/json")})
    })
    @PostMapping("/save-flight")
    public ResponseEntity<?> saveFlight(@RequestBody SaveFlightRequestDTO requestDTO) {
        try {
            SaveFlightResponseDTO responseDTO = flightService.saveFlight(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
