package com.example.planesales.Aviasales.db.repository;

import com.example.planesales.Aviasales.db.entity.FlightsForDates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightsForDatesRepository extends JpaRepository<FlightsForDates, Long> {
    boolean existsByLink(String link);
}