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
        if(existingTour.isEmpty()){
            response.setMessage("tour does not exist!");
            response.setStatusCode(204);
            return response;
        }else{
            Tour toUpdate =existingTour.get();
            toUpdate.setDuration(tourDetails.getDuration());
            toUpdate.setEndDate(tourDetails.getEndDate());
            toUpdate.setImages(tourDetails.getImages());
            toUpdate.setLocation(tourDetails.getLocation());
            toUpdate.setMaxParticipants(tourDetails.getMaxParticipants());
            toUpdate.setPrice(tourDetails.getPrice());
            toUpdate.setRatings(tourDetails.getRatings());
            toUpdate.setReviews(tourDetails.getReviews());
            toUpdate.setTitle(tourDetails.getTitle());
            toUpdate.setStartDate(tourDetails.getStartDate());
            tourDetails.setTourGuide(tourDetails.getTourGuide());

            repository.save(toUpdate);
            response.setMessage("tour updated!");
            response.setStatusCode(200);
        }
        return response;
    }

    @Override
    public TourDto findTourById(Long tourId) {
        TourDto response =new TourDto();
        Optional<Tour> existingTour =repository.findById(tourId);
        if(existingTour.isEmpty()){
            response.setMessage("tour does not exist!");
            response.setStatusCode(204);
            return response;
        }
        response.setTour(existingTour.get());
        response.setMessage("tour width id "+tourId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public TourDto deleteTourById(Long tourId) {
        return null;
    }
}
