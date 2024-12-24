package com.slippery.tourguide.service.impl;

import com.slippery.tourguide.dto.TourDto;
import com.slippery.tourguide.models.Tour;
import com.slippery.tourguide.repository.TourRepository;
import com.slippery.tourguide.service.TourService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository repository;

    public TourServiceImpl(TourRepository repository) {
        this.repository = repository;
    }

    @Override
    public TourDto createNewTour(Tour tourDetails) {
        TourDto response =new TourDto();
        repository.save(tourDetails);
        response.setMessage("new tour created");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public TourDto updateTour(Tour tourDetails,Long tourId) {
        TourDto response =new TourDto();
        Optional<Tour> existingTour =repository.findById(tourId);
        y
        return null;
    }

    @Override
    public TourDto findTourById(Long tourId) {
        return null;
    }

    @Override
    public TourDto deleteTourById(Long tourId) {
        return null;
    }
}
