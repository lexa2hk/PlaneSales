package com.example.backendcoursework.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payment_status")
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaymentStatus;

    private String statusDetails;


}