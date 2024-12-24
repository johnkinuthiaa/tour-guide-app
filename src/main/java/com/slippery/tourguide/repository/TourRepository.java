package com.slippery.tourguide.repository;

import com.slippery.tourguide.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour,Long> {
}
