package com.airline_reservation_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;

    @Column(unique = true, length = 10)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String aadhaarId;

    @Column(unique = true)
    private String passportID;

    @Column(unique = true)
    private String userName;
    private String password;
    private String address;
    // Date Format -> dd-mm-yyyy
    @Column(length = 10)
    private String dateOfBirth;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> bookedTickets;

    @ManyToOne
    @JoinColumn
    private Flight flight;
}
