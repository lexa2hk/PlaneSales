package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Company")
public class Company {

    @Id
    private String companyName;

    private String mainInfo;
    private String park;
    private String since;
    private Integer annualPassTraffic;



}