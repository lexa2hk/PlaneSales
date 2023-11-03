package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.Company;
import com.example.backendcoursework.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companiesRepository;

    /**
     * Generates a list of Company objects and fills it with 10 companies.
     *
     * @return  a list of Company objects filled with 10 companies
     */
    public List<Company> fillCompanies() {
        List<Company> companies = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Company company = new Company();
            company.setCompanyName("Company " + i);

            companies.add(company);
        }
        companiesRepository.saveAll(companies);
        return companies;

    }

    public Company getRandomCompany(){
        Iterator<Company> companies = companiesRepository.findAll().iterator();
        List<Company> list = new LinkedList<>();
        while(companies.hasNext()){
            list.add(companies.next());
        }

        return list.get((int) (list.size() * Math.random()));
    }

}