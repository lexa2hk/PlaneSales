package com.example.planesales.Controller.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ReviewRequest(String reviewObject,
                            String objectId,
                            String reviewText,
                            byte[] reviewPicture,
                            @Pattern(regexp = "^[1-5]$")
                            @NotBlank(message = "Rating can not be blank")
                            int reviewRating) {
}
