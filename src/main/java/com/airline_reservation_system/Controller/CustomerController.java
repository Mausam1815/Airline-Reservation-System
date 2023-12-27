package com.airline_reservation_system.Controller;

import com.airline_reservation_system.DTO.RequestDTO.SaveCustomerRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateCustomerRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.DeleteResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ExceptionResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveCustomerResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ShowCustomerResponseDTO;
import com.airline_reservation_system.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Tag(name = "Customer service endpoints.", description = "This controller contains all the endpoints that a 'Customer' can use.")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Add new customer.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New customer is added successfully!!", content = {@Content(schema = @Schema(implementation = SaveCustomerResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application/json")})
    })

    @PostMapping("/save-new-customer")
    public ResponseEntity<?> saveNewCustomer(@RequestBody SaveCustomerRequestDTO requestDTO) {
        try {
            SaveCustomerResponseDTO responseDTO = customerService.saveNewCustomer(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update customer details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer details updated successfully!!", content = {@Content(schema = @Schema(implementation = SaveCustomerResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application/json")})
    })
    @PutMapping("/update-customer")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequestDTO requestDTO) {
        try {
            SaveCustomerResponseDTO responseDTO = customerService.updateCustomer(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete customer from database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully!!", content = {@Content(schema = @Schema(implementation = DeleteResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application/json")})
    })
    @DeleteMapping("/delete-customer")
    public ResponseEntity<?> deleteCustomer(@RequestParam Long id) {
        try {
            DeleteResponseDTO responseDTO = customerService.deleteCustomer(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get Customer by Customer ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Customer found successfully!!", content = {@Content(schema = @Schema(implementation = ShowCustomerResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Error!! check details", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application/json")})
    })
    @GetMapping("/get-customer-by-id")
    public ResponseEntity<?> getCustomerById(@RequestParam Long id) {
        try {
            ShowCustomerResponseDTO responseDTO = customerService.getCustomerById(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
        }
    }
}
