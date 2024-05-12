package com.example.planesales.Entity;

import com.example.planesales.Aviasales.db.entity.FlightsForDates;
import jakarta.persistence.*;

@Entity
public class DoneFlight {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @OneToOne
    private FlightsForDates flight;
}
