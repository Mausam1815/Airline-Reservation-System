package com.airline_reservation_system.Service;

import com.airline_reservation_system.DTO.RequestDTO.SaveAdminRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateAdminRequestDTO;
import com.airline_reservation_system.Enum.Gender;
import com.airline_reservation_system.Exception.AdminNotFoundException;
import com.airline_reservation_system.Model.Admin;
import com.airline_reservation_system.DTO.ResponseDTO.SaveAdminResponseDTO;
import com.airline_reservation_system.Repository.AdminRepository;
import com.airline_reservation_system.Validaton.DateValidation.ValidateDate;
import com.airline_reservation_system.Validaton.EmailValidation.ValidateEmail;
import com.airline_reservation_system.Validaton.GenderValidation.ValidateGender;
import com.airline_reservation_system.Validaton.PasswordValidation.ValidatePassword;
import com.airline_reservation_system.Validaton.PhoneNumberValidation.ValidatePhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    public SaveAdminResponseDTO saveNewAdmin(SaveAdminRequestDTO adminRequestDTO) {
        try {
            ValidateEmail.validateEmail(adminRequestDTO.getEmail());
            ValidatePhoneNumber.validatePhoneNumber(adminRequestDTO.getPhoneNumber());
            ValidateGender.validateGender(adminRequestDTO.getGender().toString());
            ValidateDate.validateDate(adminRequestDTO.getDateOfBirth());
            ValidatePassword.validatePassword(adminRequestDTO.getPassword());

            Admin admin = new Admin();
            admin.setFirstName(adminRequestDTO.getFirstName());
            admin.setLastName(adminRequestDTO.getLastName());
            admin.setGender(adminRequestDTO.getGender().toString().toUpperCase());
            admin.setPhoneNumber(adminRequestDTO.getPhoneNumber());
            admin.setEmail(adminRequestDTO.getEmail());
            admin.setAadhaarId(adminRequestDTO.getAadhaarId());
            admin.setUserName(adminRequestDTO.getUserName());
            admin.setPassword(adminRequestDTO.getPassword());
            admin.setAddress(adminRequestDTO.getAddress());
            admin.setDateOfBirth(adminRequestDTO.getDateOfBirth());

            adminRepository.save(admin);
            emailService.saveNewAdminMail(admin);

            SaveAdminResponseDTO adminResponseDTO = new SaveAdminResponseDTO();
            adminResponseDTO.setFirstName(admin.getFirstName());
            adminResponseDTO.setLastName(admin.getLastName());
            adminResponseDTO.setEmail(admin.getEmail());
            adminResponseDTO.setUserName(admin.getUserName());

            return adminResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SaveAdminResponseDTO updateAdmin(UpdateAdminRequestDTO requestDTO) {
        try{
            Admin admin = adminRepository.findById(requestDTO.getId()).orElseThrow();
            admin.setPhoneNumber(requestDTO.getPhoneNumber());
            admin.setEmail(requestDTO.getEmail());
            admin.setPassword(requestDTO.getPassword());
            admin.setAddress(requestDTO.getAddress());

            adminRepository.save(admin);
            emailService.updateAdminMail(admin);

            SaveAdminResponseDTO responseDTO = new SaveAdminResponseDTO();
            responseDTO.setFirstName(admin.getFirstName());
            responseDTO.setLastName(admin.getLastName());
            responseDTO.setEmail(admin.getEmail());
            responseDTO.setUserName(admin.getUserName());

            return responseDTO;
        }catch (Exception e) {
            throw new AdminNotFoundException("Admin not found. Please enter correct Admin Id.");
        }
    }
}
