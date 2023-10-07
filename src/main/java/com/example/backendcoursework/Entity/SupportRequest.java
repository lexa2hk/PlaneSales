package com.example.backendcoursework.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SupportRequest")
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupportRequest;

    private String requestText;

    @ManyToOne
    @JoinColumn(name = "User_idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "SupportEmployee_idSupportEmployee")
    private SupportEmployee supportEmployee;



}