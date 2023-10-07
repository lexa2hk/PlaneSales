package com.example.backendcoursework.Entity;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "Flight_has_Company")
@IdClass(FlightCompanyPK.class)
public class FlightCompany implements Serializable {

    //TODO: deal with m2m relationship
    @Id
    @ManyToOne
    @JoinColumn(name = "Flight_idFlight", nullable = false)
    private Flight flight;

    @Id
    @ManyToOne
    @JoinColumn(name = "Company_companyName", nullable = false)
    private Company company;



}