package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;

    private Date creationDate;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "payment_status_id_payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id_user", nullable = false)
    private User user;



}