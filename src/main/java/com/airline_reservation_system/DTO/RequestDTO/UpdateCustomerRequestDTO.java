package com.airline_reservation_system.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCustomerRequestDTO {
    private Long id;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
}
