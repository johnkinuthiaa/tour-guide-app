package com.slippery.tourguide.service;

import com.slippery.tourguide.dto.ReviewDto;
import com.slippery.tourguide.models.RatingsAndReviews;

public interface ReviewService {
    ReviewDto createNewReview(RatingsAndReviews reviews,Long userId,Long tourId);
    ReviewDto updateReview(RatingsAndReviews reviews,Long reviewId,Long userId);
    ReviewDto deleteReview(Long reviewId,Long userId);
    ReviewDto getReviewById(Long reviewId,Long userId);

}
