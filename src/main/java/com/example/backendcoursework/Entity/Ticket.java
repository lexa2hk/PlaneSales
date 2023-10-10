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
    @JoinColumn(name = "place_id_place", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "order_id_order", nullable = false)
    private Orders order;


}