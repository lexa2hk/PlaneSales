package com.example.backendcoursework.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReceipt;

    private int total;
    private Date closeTime;

    @ManyToOne
    @JoinColumn(name = "User_idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PaymentStatus_idPaymentStatus", nullable = false)
    private PaymentStatus paymentStatus;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}