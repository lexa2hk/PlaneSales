package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.CompanyMark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyMarkRepository extends CrudRepository<CompanyMark, String> {

    Iterable<CompanyMark> findAllByCompany_CompanyName(String companyName);

    @Query("select c from CompanyMark c where c.userName = ?1")
    List<CompanyMark> findByUserName(String userName);

    long deleteByUserName(String userName);
}