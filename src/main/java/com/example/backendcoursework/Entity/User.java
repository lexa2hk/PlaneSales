package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String passportData;
    private String name;
    private String surname;
    private String patronymic;
    private String birthDate;
    private String exemption;


}