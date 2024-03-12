package com.example.planesales;
import com.example.planesales.Entity.Company;
import com.example.planesales.Repository.CompanyRepository;
import com.example.planesales.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

//    @Test
//    public void testFillCompanies() {
//        // Arrange
//        List<Company> companies = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            Company company = new Company();
//            company.setCompanyName("Company " + i);
//            companies.add(company);
//        }
//        when(companyRepository.saveAll(org.mockito.ArgumentMatchers.anyCollection())).thenReturn(companies);
//
//        // Act
//        List<Company> result = companyService.fillCompanies();
//
//        // Assert
//        assertEquals(Arrays.toString(companies.toArray()), Arrays.toString(result.toArray()));
//    }

    @Test
    public void testGetRandomCompany() {
        // Arrange
        List<Company> allCompanies = Collections.singletonList(new Company()); // Sample list of companies
        when(companyRepository.findAll()).thenReturn(allCompanies);

        // Act
        Company result = companyService.getRandomCompany();

        // Assert
        assertEquals(allCompanies.get(0), result);
    }

    @Test
    public void testGetRandomCompanyWithEmptyRepository() {
        // Arrange
        when(companyRepository.findAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> companyService.getRandomCompany());
    }

}