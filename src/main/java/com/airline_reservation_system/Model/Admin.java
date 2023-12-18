package com.airline_reservation_system.Model;

import com.airline_reservation_system.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true, length = 10)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String aadhaarId;

    @Column(unique = true)
    private String userName;
    private String password;
    private String address;
    // Date Format -> dd-mm-yyyy
    @Column(length = 10)
    private String dateOfBirth;
}
