package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Getter;

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



}