package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;

    private Date creationDate;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "PaymentStatus_idPaymentStatus", nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "User_idUser", nullable = false)
    private User user;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}