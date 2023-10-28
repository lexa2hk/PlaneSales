package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
}