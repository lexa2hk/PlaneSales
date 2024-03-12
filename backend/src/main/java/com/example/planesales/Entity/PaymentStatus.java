package com.example.planesales.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payment_status")
@Deprecated
public class PaymentStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaymentStatus;


    @Enumerated(EnumType.STRING)
    private PaymentState statusDetails;


}