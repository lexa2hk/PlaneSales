package com.example.planesales.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
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


    @Enumerated(EnumType.STRING)
    private PaymentState paymentStatus;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}