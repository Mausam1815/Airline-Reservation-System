package com.airline_reservation_system.DTO.ResponseDTO;

import com.airline_reservation_system.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowCustomerResponseDTO {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private String userName;
    private String dateOfBirth;
}
