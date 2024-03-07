package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Plane;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlaneRepository extends CrudRepository<Plane, Integer> {
    Iterable<Plane> findAllByFlight_Route(String route);

    @Query("SELECT p FROM Plane p JOIN p.flight f WHERE f.route = :route")
    Iterable<Plane> findByFlightCode(@Param("route") String route);


}