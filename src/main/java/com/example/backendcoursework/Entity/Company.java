package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private String companyName;

    private String mainInfo;
    private String park;
    private String since;
    private Integer annualPassTraffic;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "flight_has_company",
            joinColumns = @JoinColumn(name = "company_company_name", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "flight_id_flight", nullable = false))
    private List<Flight> flights = new ArrayList<>();

}