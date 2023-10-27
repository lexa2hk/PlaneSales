package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.CompanyMark;
import com.example.backendcoursework.Repository.CompanyMarkRepository;
import com.example.backendcoursework.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final CompanyRepository companyRepository;
    private final CompanyMarkRepository companyMarkRepository;

    public List<CompanyMark> getCompanyMarks(){
        List<CompanyMark> companyMarks = new LinkedList<>();
        companyMarkRepository.findAll().forEach(companyMarks::add);
        return companyMarks;
    }

    public List<CompanyMark> getCompanyMarksByCompany(String companyName){
        List<CompanyMark> companyMarks = new LinkedList<>();
        companyMarkRepository.findAllByCompany_CompanyName(companyName).forEach(companyMarks::add);
        return companyMarks;
    }

    public CompanyMark addCompanyMark(String username, String companyName, String mark, String markText){
        CompanyMark companyMark = new CompanyMark();
        companyMark.setUserName(username);
        companyMark.setCompany(companyRepository.findByCompanyName(companyName));
        companyMark.setMark(mark);
        companyMark.setMarkText(markText);
        return companyMarkRepository.save(companyMark);

    }

    //todo: add deletion method like banning user

}
