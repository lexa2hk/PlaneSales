package com.example.planesales.Repository;

import com.example.planesales.Entity.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, String> {
    Iterable<Flight> findAllByRoute(String route);

    boolean existsByRoute(String route);

}