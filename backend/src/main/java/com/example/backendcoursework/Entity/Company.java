package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    private String companyName;

    private String mainInfo;
    private String park;
    private String since;
    private Integer annualPassTraffic;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(
//            name = "flight_company",
//            joinColumns = @JoinColumn(name = "company_company_name", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "flight_id_flight", nullable = false)
//    )
    private List<Flight> flights = new ArrayList<>();

}