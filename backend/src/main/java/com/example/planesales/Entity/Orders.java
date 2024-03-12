package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    private String flightRoute;

//    @ManyToOne
//    @JoinColumn(name = "payment_status_id_payment_status", nullable = false)
//    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id_user", nullable = false)
    private User user;


    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets = new LinkedList<>();

}