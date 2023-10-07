package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CompanyMark")
public class CompanyMark {

    @Id
    private String idCompanyMark;

    private String userName;
    private String mark;
    private String markText;

    @ManyToOne
    @JoinColumn(name = "Company_companyName", nullable = false)
    private Company company;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}