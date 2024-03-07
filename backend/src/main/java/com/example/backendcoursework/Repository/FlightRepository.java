package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, String> {
    Iterable<Flight> findAllByRoute(String route);

    boolean existsByRoute(String route);

}