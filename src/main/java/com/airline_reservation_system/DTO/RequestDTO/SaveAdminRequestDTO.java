package com.airline_reservation_system.DTO.RequestDTO;

import com.airline_reservation_system.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SaveAdminRequestDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String aadhaarId;
    private String userName;
    private String password;
    private String address;
    private String dateOfBirth;
}
