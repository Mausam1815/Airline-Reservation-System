package com.airline_reservation_system.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveAdminResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
}
