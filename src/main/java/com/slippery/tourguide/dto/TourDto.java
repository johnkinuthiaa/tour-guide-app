package com.slippery.tourguide.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.tourguide.models.Tour;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourDto {
    private String message;
    private String errorMessage;
    private Integer statusCode;
    private Tour tour;
    private List<Tour> tourList;
}
