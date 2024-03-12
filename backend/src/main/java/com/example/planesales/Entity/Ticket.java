package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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