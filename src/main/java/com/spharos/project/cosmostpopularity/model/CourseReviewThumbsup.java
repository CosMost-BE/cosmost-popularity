package com.spharos.project.cosmostpopularity.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CourseReviewThumbsup {

    private Long id;
    private Long authId;
    private Long courseReviewId;
    private Long courseReviewThumbsupCount;

}
