package com.example.planesales.Repository;

import com.example.planesales.Entity.Review;
import com.example.planesales.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
}