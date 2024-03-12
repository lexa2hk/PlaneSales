package com.example.planesales.Repository;

import com.example.planesales.Entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String>{
    Company findByCompanyName(String companyName);
}