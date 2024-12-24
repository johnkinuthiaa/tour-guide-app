package com.slippery.tourguide.service;

import com.slippery.tourguide.dto.TourDto;
import com.slippery.tourguide.models.Tour;

public interface TourService {
    TourDto createNewTour(Tour tourDetails);
    TourDto updateTour(Tour tourDetails,Long tourId);
    TourDto findTourById(Long tourId);
    TourDto deleteTourById(Long tourId);
}
