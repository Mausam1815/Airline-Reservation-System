package com.airline_reservation_system.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveCustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String aadhaarId;
    private String passportId;
    private String userName;
    private String password;
    private String address;
    private String dateOfBirth;
}
