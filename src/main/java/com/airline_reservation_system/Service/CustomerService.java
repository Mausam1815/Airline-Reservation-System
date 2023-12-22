package com.airline_reservation_system.Service;

import com.airline_reservation_system.DTO.RequestDTO.SaveCustomerRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveCustomerResponseDTO;
import com.airline_reservation_system.Enum.Gender;
import com.airline_reservation_system.Model.Customer;
import com.airline_reservation_system.Repository.CustomerRepository;
import com.airline_reservation_system.Validaton.DateValidation.ValidateDate;
import com.airline_reservation_system.Validaton.EmailValidation.ValidateEmail;
import com.airline_reservation_system.Validaton.GenderValidation.ValidateGender;
import com.airline_reservation_system.Validaton.PasswordValidation.ValidatePassword;
import com.airline_reservation_system.Validaton.PhoneNumberValidation.ValidatePhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;

    public SaveCustomerResponseDTO saveNewCustomer(SaveCustomerRequestDTO requestDTO) {
        try {
            ValidateEmail.validateEmail(requestDTO.getEmail());
            ValidatePhoneNumber.validatePhoneNumber(requestDTO.getPhoneNumber());
            ValidateGender.validateGender(requestDTO.getGender());
            ValidateDate.validateDate(requestDTO.getDateOfBirth());
            ValidatePassword.validatePassword(requestDTO.getPassword());

            Customer customer = new Customer();
            customer.setFirstName(requestDTO.getFirstName());
            customer.setLastName(requestDTO.getLastName());
            customer.setGender(Gender.valueOf(requestDTO.getGender().toUpperCase()));
            customer.setPhoneNumber(requestDTO.getPhoneNumber());
            customer.setEmail(requestDTO.getEmail());
            customer.setAadhaarId(requestDTO.getAadhaarId());
            customer.setPassportID(requestDTO.getPassportId());
            customer.setUserName(requestDTO.getUserName());
            customer.setPassword(requestDTO.getPassword());
            customer.setAddress(requestDTO.getAddress());
            customer.setDateOfBirth(requestDTO.getDateOfBirth());
            customer.setBookedTickets(new ArrayList<>());
            customer.setFlight(null);

            customerRepository.save(customer);
            emailService.saveNewCustomerMail(customer);

            SaveCustomerResponseDTO responseDTO = new SaveCustomerResponseDTO();
            responseDTO.setFirstName(customer.getFirstName());
            responseDTO.setLastName(customer.getLastName());
            responseDTO.setUserName(customer.getUserName());
            responseDTO.setEmail(customer.getEmail());

            return responseDTO;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
