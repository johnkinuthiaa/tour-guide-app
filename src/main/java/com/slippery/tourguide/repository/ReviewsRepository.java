package com.slippery.tourguide.repository;

import com.slippery.tourguide.models.RatingsAndReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<RatingsAndReviews,Long> {
}
