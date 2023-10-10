package com.example.backendcoursework.Entity;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "flight_has_company")
@IdClass(FlightCompanyPK.class)
public class FlightCompany implements Serializable {

    //TODO: deal with m2m relationship
    @Id
    @ManyToOne
    @JoinColumn(name = "flight_id_flight", nullable = false)
    private Flight flight;

    @Id
    @ManyToOne
    @JoinColumn(name = "company_company_name", nullable = false)
    private Company company;



}