package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.LinkedList;

@Entity
@Table(name = "flight")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFlight;

    private String route;
    private int passengerQty;
    private Integer duration;

    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(
//            name = "flight_company",
//            joinColumns = @JoinColumn(name = "flight_id_flight", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "company_company_name", nullable = false)
//    )
    private List<Company> companies = new LinkedList<>();



}