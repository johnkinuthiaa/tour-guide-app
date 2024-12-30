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
import java.util.Objects;
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
    public ReviewDto updateReview(RatingsAndReviews reviews,Long userId,Long tourId,Long reviewId) {
        ReviewDto response =new ReviewDto();
        Optional<RatingsAndReviews> reviews1 =repository.findById(reviewId);
        Optional<Tour> tour =tourRepository.findById(tourId);
        Optional<User> user =userRepository.findById(userId);
        if(reviews1.isEmpty()){
            response.setErrorMessage("Review not found");
            response.setStatusCode(404);
            return response;
        }
        if(user.isEmpty()){
            response.setErrorMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(tour.isEmpty()){
            response.setErrorMessage("Tour not found");
            response.setStatusCode(404);
            return response;
        }
        if(!reviews1.get().getUser().getId().equals(user.get().getId())){
            response.setErrorMessage("User does not have review with id "+reviewId);
            response.setStatusCode(404);
            return response;
        }
        reviews1.get().setReviewText(reviews.getReviewText());
        repository.save(reviews1.get());
        response.setMessage("Review updated");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ReviewDto deleteReview(Long reviewId, Long userId,Long tourId) {
        Optional<RatingsAndReviews> reviews1 =repository.findById(reviewId);
        Optional<Tour> tour =tourRepository.findById(tourId);
        Optional<User> user =userRepository.findById(userId);
        ReviewDto response =new ReviewDto();
        if(reviews1.isEmpty()){
            response.setErrorMessage("Review not found");
            response.setStatusCode(404);
            return response;
        }
        if(user.isEmpty()){
            response.setErrorMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(tour.isEmpty()){
            response.setErrorMessage("Tour not found");
            response.setStatusCode(404);
            return response;
        }
        if(Objects.equals(reviews1.get().getTour().getId(), tourId) && Objects.equals(reviews1.get().getUser().getId(), userId)){
            response.setErrorMessage("Review does not belong to the user ot tour");
            response.setStatusCode(500);
            return response;
        }
        repository.deleteById(reviewId);
        response.setMessage("Review deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ReviewDto getReviewById(Long reviewId, Long userId,Long tourId) {
        return null;
    }
}
