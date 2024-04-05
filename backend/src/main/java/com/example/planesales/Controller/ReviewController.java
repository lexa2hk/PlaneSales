package com.example.planesales.Controller;

import com.example.planesales.Controller.Request.ReviewRequest;
import com.example.planesales.Controller.Response.ReviewResponse;
import com.example.planesales.DTO.CompanyMarkDto;
import com.example.planesales.Entity.CompanyMark;
import com.example.planesales.Entity.Review;
import com.example.planesales.Service.ReviewService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/review")
@Slf4j
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
        Review createdReview = reviewService.createReview(reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(createdReview));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> existingReview = reviewService.getReviewById(id,true);
        if (existingReview.isPresent()) {
            review.setReviewId(id);
            Review updatedReview = reviewService.updateReview(review);
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    private ReviewResponse toResponse(Review review) {
        return new ReviewResponse(review.getReviewObjective(), review.getObjectId(), review.getReviewText(), review.getReviewRating());
    }
}
