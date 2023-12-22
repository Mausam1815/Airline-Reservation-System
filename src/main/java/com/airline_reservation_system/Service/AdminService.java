package com.airline_reservation_system.Service;

import com.airline_reservation_system.DTO.RequestDTO.SaveAdminRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateAdminRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.DeleteAdminResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ShowAdminResponseDTO;
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

import java.util.ArrayList;
import java.util.List;

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
            ValidateGender.validateGender(adminRequestDTO.getGender());
            ValidateDate.validateDate(adminRequestDTO.getDateOfBirth());
            ValidatePassword.validatePassword(adminRequestDTO.getPassword());

            Admin admin = new Admin();
            admin.setFirstName(adminRequestDTO.getFirstName());
            admin.setLastName(adminRequestDTO.getLastName());
            admin.setGender(Gender.valueOf(adminRequestDTO.getGender().toUpperCase()));
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
            ValidateEmail.validateEmail(requestDTO.getEmail());
            ValidatePhoneNumber.validatePhoneNumber(requestDTO.getPhoneNumber());
            ValidatePassword.validatePassword(requestDTO.getPassword());

            admin.setEmail(requestDTO.getEmail());
            admin.setPhoneNumber(requestDTO.getPhoneNumber());
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
            throw new RuntimeException(e);
        }
    }
    public DeleteAdminResponseDTO deleteAdmin(Long id) {
        try{
            Admin admin = adminRepository.findById(id).orElseThrow();
            emailService.deleteAdminMail(admin);
            adminRepository.delete(admin);

            return new DeleteAdminResponseDTO("Admin deleted successfully.");
        }catch (Exception e) {
            throw new AdminNotFoundException("Admin not found. Please enter correct admin id.");
        }
    }

    public ShowAdminResponseDTO getAdminById(Long id) {
        try {
            Admin admin = adminRepository.findById(id).orElseThrow();
            ShowAdminResponseDTO responseDTO = new ShowAdminResponseDTO();
            responseDTO.setId(admin.getId());
            responseDTO.setName(admin.getFirstName() + " " + admin.getLastName());
            responseDTO.setGender(admin.getGender().toString());
            responseDTO.setPhoneNumber(admin.getPhoneNumber());
            responseDTO.setEmail(admin.getEmail());
            responseDTO.setUserName(admin.getUserName());
            responseDTO.setDateOfBirth(admin.getDateOfBirth());

            return responseDTO;
        }catch (Exception e) {
            throw new AdminNotFoundException("Admin not found. Please enter correct admin id.");
        }
    }

    public List<ShowAdminResponseDTO> getAllAdminsInfo() {
        List<ShowAdminResponseDTO> responseDTOList = new ArrayList<>();
        List<Admin> adminList = adminRepository.findAll();
        for(Admin admin : adminList) {
            ShowAdminResponseDTO responseDTO = new ShowAdminResponseDTO();
            responseDTO.setId(admin.getId());
            responseDTO.setName(admin.getFirstName() + " " + admin.getLastName());
            responseDTO.setGender(admin.getGender().toString());
            responseDTO.setPhoneNumber(admin.getPhoneNumber());
            responseDTO.setEmail(admin.getEmail());
            responseDTO.setUserName(admin.getUserName());
            responseDTO.setDateOfBirth(admin.getDateOfBirth());

            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }
}
