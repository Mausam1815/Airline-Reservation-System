package com.airline_reservation_system.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ShowAdminResponseDTO {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private String userName;
    private String dateOfBirth;
}
