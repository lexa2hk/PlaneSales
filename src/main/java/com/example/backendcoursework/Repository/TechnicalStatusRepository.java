package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.TechnicalStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TechnicalStatusRepository extends CrudRepository<TechnicalStatus, Integer> {
    @Query("select t from TechnicalStatus t where t.status = ?1")
    Optional<TechnicalStatus> findByStatus(String status);
//    List<TechnicalStatus> saveAll(List<TechnicalStatus> technicalStatus);
}