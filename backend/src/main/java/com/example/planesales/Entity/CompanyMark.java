package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company_mark")
public class CompanyMark {

    @Id
    @GeneratedValue
    private int idCompanyMark;

    private String userName;
    private String mark;
    private String markText;

    @ManyToOne
    @JoinColumn(name = "company_company_name", nullable = false)
    private Company company;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}