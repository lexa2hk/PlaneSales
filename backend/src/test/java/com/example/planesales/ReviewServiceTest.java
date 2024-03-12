package com.example.planesales;
import com.example.planesales.Authentication.AuthenticationService;
import com.example.planesales.Entity.Company;
import com.example.planesales.Entity.CompanyMark;
import com.example.planesales.Repository.CompanyMarkRepository;
import com.example.planesales.Repository.CompanyRepository;
import com.example.planesales.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMarkRepository companyMarkRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void testGetCompanyMarks() {
        // Arrange
        List<CompanyMark> companyMarks = Collections.singletonList(new CompanyMark());
        when(companyMarkRepository.findAll()).thenReturn(companyMarks);

        // Act
        List<CompanyMark> result = reviewService.getCompanyMarks();

        // Assert
        assertEquals(companyMarks, result);
    }

    @Test
    public void testGetCompanyMarksByCompany() {
        // Arrange
        String companyName = "ExampleCompany";
        List<CompanyMark> companyMarks = Collections.singletonList(new CompanyMark());
        when(companyMarkRepository.findAllByCompany_CompanyName(companyName)).thenReturn(companyMarks);

        // Act
        List<CompanyMark> result = reviewService.getCompanyMarksByCompany(companyName);

        // Assert
        assertEquals(companyMarks, result);
    }

    @Test
    public void testAddCompanyMark() {
        // Arrange
        String username = "testuser";
        String companyName = "ExampleCompany";
        String mark = "5";
        String markText = "Excellent service";
        CompanyMark companyMark = new CompanyMark();
        when(companyRepository.findByCompanyName(companyName)).thenReturn(new Company());
        when(companyMarkRepository.save(org.mockito.ArgumentMatchers.any(CompanyMark.class))).thenReturn(companyMark);

        // Act
        CompanyMark result = reviewService.addCompanyMark(username, companyName, mark, markText);

        // Assert
        assertEquals(companyMark, result);
    }

    @Test
    public void testDeleteCompanyMark() {
        // Arrange
        String username = "testuser";
        List<CompanyMark> marksToDelete = Collections.singletonList(new CompanyMark());
        when(companyMarkRepository.findByUserName(username)).thenReturn(marksToDelete);

        // Act
        List<CompanyMark> result = reviewService.deleteCompanyMark(username);

        // Assert
        assertEquals(marksToDelete, result);
    }

    @Test
    public void testBanUser() {
        // Arrange
        String username = "testuser";
        List<CompanyMark> marksToDelete = Collections.singletonList(new CompanyMark());
        when(companyMarkRepository.findByUserName(username)).thenReturn(marksToDelete);

        // Act
        reviewService.banUser(username);

        // Assert that the user is disabled by your authentication service (you may need to mock the authentication service)
        // Example: verify(authenticationService).disableUser(username);
    }

}
