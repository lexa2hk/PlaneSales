package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    private String ticketLink;

    @ManyToOne
    @JoinColumn(name = "Place_idPlace", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "Order_idOrder", nullable = false)
    private Order order;


}