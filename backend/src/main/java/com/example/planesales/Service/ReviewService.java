package com.example.planesales.Service;

import com.example.planesales.Controller.Request.ReviewRequest;
import com.example.planesales.Controller.Response.ReviewResponse;
import com.example.planesales.Entity.Review;
import com.example.planesales.Entity.ReviewObjective;
import com.example.planesales.Entity.User;
import com.example.planesales.Repository.ReviewRepository;
import com.example.planesales.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    public Optional<Review> getReviewById(Long reviewId, boolean raw) throws IllegalArgumentException {
        return reviewRepository.findById(reviewId);
    }

    public ReviewResponse getReviewById(Long reviewId) throws IllegalArgumentException {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isEmpty()) {
            throw new IllegalArgumentException("Review with id " + reviewId + " does not exist");
        }
        return toResponse(reviewOptional.get());
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review createReview(ReviewRequest reviewRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Review review = new Review();
        Optional<User> userOptional = userRepository.findByEmail(currentUserName);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        review.setUser(userOptional.get());
        review.setReviewObjective(ReviewObjective.valueOf(reviewRequest.reviewObject()));
        review.setReviewRating(reviewRequest.reviewRating());
        review.setObjectId(reviewRequest.objectId());
        review.setReviewText(reviewRequest.reviewText());
        review.setReviewPicture(reviewRequest.reviewPicture());
        return reviewRepository.save(review);
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewResponse toResponse(Review review) {
        return new ReviewResponse(review.getReviewObjective(), review.getObjectId(), review.getReviewText(), review.getReviewRating());
    }
}