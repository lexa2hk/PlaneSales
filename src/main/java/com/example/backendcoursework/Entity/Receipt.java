package com.example.backendcoursework.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReceipt;

    private int total;
    private Date closeTime;

    @ManyToOne
    @JoinColumn(name = "user_id_user", nullable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "payment_status_id_payment_status", nullable = false)
//    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentStatus;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}