package com.slippery.tourguide.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Tour {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String title;
    @Lob
    private String location;
    private Long price;
    private Integer duration;
    private Integer maxParticipants;
//    @Lob
//    private List<Byte[]> images;
    private int ratings;
    @ElementCollection
    private List<String> reviews;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonBackReference
    @ManyToOne
    private User tourGuide;


}
