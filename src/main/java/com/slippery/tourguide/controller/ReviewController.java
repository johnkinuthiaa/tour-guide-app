package com.slippery.tourguide.controller;

import com.slippery.tourguide.dto.ReviewDto;
import com.slippery.tourguide.models.RatingsAndReviews;
import com.slippery.tourguide.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }
    @PostMapping("/new/review")
    public ResponseEntity<ReviewDto> createNewReview(@RequestBody RatingsAndReviews reviews,
                                                     @RequestParam Long userId,
                                                     @RequestParam Long tourId){
        return ResponseEntity.ok(service.createNewReview(reviews, userId, tourId));

    }
}
