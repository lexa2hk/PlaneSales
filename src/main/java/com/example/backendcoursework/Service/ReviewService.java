package com.example.backendcoursework.Service;

import com.example.backendcoursework.Authentication.AuthenticationService;
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

    //todo impove rewiews

    private final CompanyRepository companyRepository;
    private final CompanyMarkRepository companyMarkRepository;
    private final AuthenticationService authenticationService;

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

    public void banUser(String username) {
        // First, you need to find and delete any company marks associated with the banned user
        List<CompanyMark> marksToDelete = companyMarkRepository.findByUserName(username);
        companyMarkRepository.deleteAll(marksToDelete);

        //disable user
        authenticationService.disableUser(username);
//        User userToBan = userRepository.findByUsername(username);
//        if (userToBan != null) {
//            userToBan.setBanned(true); // Set the 'banned' attribute to true
//            userRepository.save(userToBan); // Save the user with the updated status
//        }
    }

}
