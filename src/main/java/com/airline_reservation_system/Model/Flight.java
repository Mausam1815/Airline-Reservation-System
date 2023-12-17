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
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightName;
    private int noOfSeats;
    private String arrivalTo;
    private String departureFrom;

    @Column(length = 5)
    private String arrivalTime;

    @Column(length = 5)
    private String departureTime;

    private double fare;
    private String duration;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> flightsSoldTickets;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Customer> customerList;
}
