package com.example.backendcoursework.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PaymentStatus")
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaymentStatus;

    private String statusDetails;


}