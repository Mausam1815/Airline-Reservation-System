package com.airline_reservation_system.Controller;

import com.airline_reservation_system.DTO.RequestDTO.SaveAdminRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateAdminRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ExceptionResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveAdminResponseDTO;
import com.airline_reservation_system.Service.AdminService;
import com.airline_reservation_system.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

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

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateAdmin(UpdateAdminRequestDTO requestDTO) {
        try{
            SaveAdminResponseDTO responseDTO = adminService.updateAdmin(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
