package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.CompanyMark;
import org.springframework.data.repository.CrudRepository;

public interface CompanyMarkRepository extends CrudRepository<CompanyMark, String> {
    Iterable<CompanyMark> findAllByCompany_CompanyName(String companyName);
}