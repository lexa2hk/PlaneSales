package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "flight")
@Data
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "flight_has_company",
            joinColumns = @JoinColumn(name = "flight_id_flight", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "company_company_name", nullable = false))
    private List<Company> companies;



}