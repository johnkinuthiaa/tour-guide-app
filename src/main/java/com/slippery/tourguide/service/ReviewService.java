package com.slippery.tourguide.service;

import com.slippery.tourguide.dto.ReviewDto;
import com.slippery.tourguide.models.RatingsAndReviews;

public interface ReviewService {
    ReviewDto createNewReview(RatingsAndReviews reviews,Long userId,Long tourId);
    ReviewDto updateReview(RatingsAndReviews reviews,Long userId,Long tourId,Long reviewId);
    ReviewDto deleteReview(Long reviewId,Long userId,Long tourId);
    ReviewDto getReviewById(Long reviewId,Long userId,Long tourId);

}
