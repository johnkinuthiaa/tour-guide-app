package com.slippery.tourguide.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.tourguide.models.RatingsAndReviews;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    private int statusCode;
    private String message;
    private String errorMessage;
    private RatingsAndReviews ratings;
    private List<RatingsAndReviews> ratingsAndReviewsList;
}
