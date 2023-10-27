package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Plane;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PlaneRepository extends CrudRepository<Plane, Integer> {
    Iterable<Plane> findAllByFlight_Route(String route);
}