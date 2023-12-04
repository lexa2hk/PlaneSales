package com.example.backendcoursework.Controller;

import com.example.backendcoursework.Controller.Request.ReviewRequest;
import com.example.backendcoursework.DTO.CompanyMarkDto;
import com.example.backendcoursework.Entity.CompanyMark;
import com.example.backendcoursework.Service.ReviewService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@Slf4j
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<CompanyMarkDto>> getAllReviews(){
        List<CompanyMark> companyMarks = reviewService.getCompanyMarks();
        List<CompanyMarkDto> companyMarkDtos= companyMarks.stream()
                .map(companyMark -> CompanyMarkDto.builder()
                        .mark(companyMark.getMark())
                        .markText(companyMark.getMarkText())
                        .companyName(companyMark.getCompany().getCompanyName())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(companyMarkDtos);
    }

    @PostMapping
    public ResponseEntity<CompanyMarkDto> addReview(@RequestBody ReviewRequest request){
        CompanyMark companyMark = reviewService.addCompanyMark(
                request.getUsername(),
                request.getCompanyName(),
                request.getMark(),
                request.getMarkText());
        return ResponseEntity.ok(CompanyMarkDto.builder()
                .mark(companyMark.getMark())
                .markText(companyMark.getMarkText())
                .companyName(companyMark.getCompany().getCompanyName())
                .build());
    }

    @Hidden
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CompanyMark>> deleteReviewByUsername(@RequestParam String username){
        log.info("deleteReviewByUsername: {}", username);
        return ResponseEntity.ok(reviewService.deleteCompanyMark(username));

    }


}
