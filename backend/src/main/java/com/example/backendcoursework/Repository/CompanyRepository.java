package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Company;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String>{
    Company findByCompanyName(String companyName);
}