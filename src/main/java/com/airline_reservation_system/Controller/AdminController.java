package com.airline_reservation_system.Controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.airline_reservation_system.DTO.RequestDTO.SaveAdminRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateAdminRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.DeleteAdminResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ExceptionResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveAdminResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ShowAdminResponseDTO;
import com.airline_reservation_system.Service.AdminService;
import com.airline_reservation_system.Service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin service endpoints.", description = "This controller contains all the endpoints that an 'Admin' can use.")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Operation(summary = "Add new admin.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New admin added successfully!!", content = {@Content(schema = @Schema(implementation = SaveAdminResponseDTO.class), mediaType = "application.json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application.json")})
    })
    @PostMapping("/save-new-admin")
    public ResponseEntity<?> saveNewAdmin(@RequestBody SaveAdminRequestDTO requestDTO) {
        try{
            SaveAdminResponseDTO responseDTO = adminService.saveNewAdmin(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update admin details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin details updated successfully!!", content = {@Content(schema = @Schema(implementation = SaveAdminResponseDTO.class), mediaType = "applicaton/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application.json")})
    })
    @PutMapping("/update-admin")
    public ResponseEntity<?> updateAdmin(@RequestBody UpdateAdminRequestDTO requestDTO) {
        try{
            SaveAdminResponseDTO responseDTO = adminService.updateAdmin(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete admin from database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin deleted Successfully!!", content = {@Content(schema = @Schema(implementation = DeleteAdminResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application.json")})
    })
    @DeleteMapping("/delete-admin")
    public ResponseEntity<?> deleteAdmin(@RequestParam Long id) {
        try{
            DeleteAdminResponseDTO responseDTO = adminService.deleteAdmin(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get admin by 'admin id'.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin found.", content = {@Content(schema = @Schema(implementation = ShowAdminResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Error!! check details.", content = {@Content(schema = @Schema(implementation = ExceptionResponseDTO.class), mediaType = "application.json")})
    })
    @GetMapping("/get-admin-by-id")
    public ResponseEntity<?> getAdminById(Long id) {
        try {
            ShowAdminResponseDTO responseDTO = adminService.getAdminById(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all the admins present in database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All admins found.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ShowAdminResponseDTO.class)), mediaType = "application/json")})
    })
    @GetMapping("/get-all-admins-info")
    public ResponseEntity<List<?>> getAllAdminsInfo() {
        List<ShowAdminResponseDTO> responseDTOList = adminService.getAllAdminsInfo();
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }
}
