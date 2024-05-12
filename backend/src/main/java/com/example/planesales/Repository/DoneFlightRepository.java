package com.example.planesales.Repository;

import com.example.planesales.Entity.DoneFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneFlightRepository extends JpaRepository<DoneFlight, Long> {
}