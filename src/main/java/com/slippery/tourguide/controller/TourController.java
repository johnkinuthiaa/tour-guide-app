package com.slippery.tourguide.controller;

import com.slippery.tourguide.dto.TourDto;
import com.slippery.tourguide.models.Tour;
import com.slippery.tourguide.service.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tour")
@CrossOrigin
public class TourController {
    /*
    TourDto createNewTour(Tour tourDetails);
    TourDto updateTour(Tour tourDetails,Long tourId);
    TourDto findTourById(Long tourId);
    TourDto deleteTourById(Long tourId);
     */
    private final TourService service;

    public TourController (TourService service){
        this.service=service;
    }
    @PostMapping("/create/tour")
    public ResponseEntity<TourDto> createNewTour(@RequestBody Tour tourDetails){
        return ResponseEntity.ok(service.createNewTour(tourDetails));
    }
//    @PutMapping("/")
}
