package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ReviewObjective reviewObjective;

    private String objectId;

    @Column(columnDefinition="text")
    private String reviewText;

    private int reviewRating;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] reviewPicture;
}
