package com.example.planesales.Controller.Response;

import com.example.planesales.Entity.ReviewObjective;

public record ReviewResponse(ReviewObjective reviewObject,
                             String objectId,
                             String reviewText,
                             int reviewRating) {
}
