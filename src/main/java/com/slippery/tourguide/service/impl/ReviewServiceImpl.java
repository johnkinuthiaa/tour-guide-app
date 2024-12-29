package com.slippery.tourguide.service.impl;

import com.slippery.tourguide.dto.ReviewDto;
import com.slippery.tourguide.models.RatingsAndReviews;
import com.slippery.tourguide.models.Tour;
import com.slippery.tourguide.models.User;
import com.slippery.tourguide.repository.ReviewsRepository;
import com.slippery.tourguide.repository.TourRepository;
import com.slippery.tourguide.repository.UserRepository;
import com.slippery.tourguide.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewsRepository repository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    public ReviewServiceImpl(ReviewsRepository repository, UserRepository userRepository, TourRepository tourRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public ReviewDto createNewReview(RatingsAndReviews reviews, Long userId,Long tourId) {
        ReviewDto response =new ReviewDto();
        Optional<User> user =userRepository.findById(userId);
        Optional<Tour> tour =tourRepository.findById(tourId);
        if(user.isEmpty()){
            response.setErrorMessage("user not found");
            response.setStatusCode(404);
            return response;
        }
        if(tour.isEmpty()){
            response.setErrorMessage("tour was not found");
            response.setStatusCode(404);
            return response;
        }
        RatingsAndReviews reviews1 =new RatingsAndReviews();
        reviews1.setReviewText(reviews.getReviewText());
        reviews1.setTime(LocalDateTime.now());
        reviews1.setTour(tour.get());
        reviews1.setUser(user.get());
        repository.save(reviews1);
        response.setMessage("Review created");
        response.setStatusCode(200);
        response.setRatings(reviews1);


        return response;
    }

    @Override
    public ReviewDto updateReview(RatingsAndReviews reviews, Long reviewId, Long userId) {
        return null;
    }

    @Override
    public ReviewDto deleteReview(Long reviewId, Long userId) {
        return null;
    }

    @Override
    public ReviewDto getReviewById(Long reviewId, Long userId) {
        return null;
    }
}
