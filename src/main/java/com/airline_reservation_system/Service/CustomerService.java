package com.airline_reservation_system.Service;

import com.airline_reservation_system.DTO.RequestDTO.SaveCustomerRequestDTO;
import com.airline_reservation_system.DTO.RequestDTO.UpdateCustomerRequestDTO;
import com.airline_reservation_system.DTO.ResponseDTO.DeleteResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.SaveCustomerResponseDTO;
import com.airline_reservation_system.DTO.ResponseDTO.ShowCustomerResponseDTO;
import com.airline_reservation_system.Enum.Gender;
import com.airline_reservation_system.Exception.CustomerNotFoundException;
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

    public SaveCustomerResponseDTO updateCustomer(UpdateCustomerRequestDTO requestDTO) {
        try {
            Customer customer = customerRepository.findById(requestDTO.getId()).orElseThrow();
            ValidateEmail.validateEmail(requestDTO.getEmail());
            ValidatePassword.validatePassword(requestDTO.getPassword());
            ValidatePhoneNumber.validatePhoneNumber(requestDTO.getPhoneNumber());

            customer.setEmail(requestDTO.getEmail());
            customer.setPhoneNumber(requestDTO.getPhoneNumber());
            customer.setPassword(requestDTO.getPassword());
            customer.setAddress(requestDTO.getAddress());

            customerRepository.save(customer);
            emailService.updateCustomerMail(customer);

            SaveCustomerResponseDTO responseDTO = new SaveCustomerResponseDTO();
            responseDTO.setFirstName(customer.getFirstName());
            responseDTO.setLastName(customer.getLastName());
            responseDTO.setEmail(customer.getEmail());
            responseDTO.setUserName(customer.getUserName());

            return responseDTO;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public DeleteResponseDTO deleteCustomer(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow();
            emailService.deleteCustomerMail(customer);
            customerRepository.delete(customer);

            return new DeleteResponseDTO("Customer deleted successfully.");
        }catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found. Please enter correct customer id.");
        }
    }

    public ShowCustomerResponseDTO getCustomerById(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow();
            ShowCustomerResponseDTO responseDTO = new ShowCustomerResponseDTO();
            responseDTO.setId(customer.getId());
            responseDTO.setName(customer.getFirstName() + " " + customer.getLastName());
            responseDTO.setGender(customer.getGender().toString());
            responseDTO.setPhoneNumber(customer.getPhoneNumber());
            responseDTO.setEmail(customer.getEmail());
            responseDTO.setUserName(customer.getUserName());
            responseDTO.setDateOfBirth(customer.getDateOfBirth());

            return responseDTO;
        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found. Please enter correct Customer id.");
        }

    }
}
