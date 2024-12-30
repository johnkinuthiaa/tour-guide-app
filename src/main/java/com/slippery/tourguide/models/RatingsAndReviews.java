package com.slippery.tourguide.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingsAndReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String reviewText;
    private LocalDateTime time;
    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference
    private Tour tour;
}
