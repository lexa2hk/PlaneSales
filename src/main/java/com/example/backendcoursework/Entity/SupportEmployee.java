package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "SupportEmployee")
public class SupportEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupportEmployee;

    private int responseStat;
    private boolean accessible;
    private Date lastSeen;



}