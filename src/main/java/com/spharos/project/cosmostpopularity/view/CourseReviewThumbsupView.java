package com.spharos.project.cosmostpopularity.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseReviewThumbsupView {

    private Long authId;
    private Long courseReviewId;

    public CourseReviewThumbsupView(CourseReviewThumbsupEntity entity) {
        this.authId = entity.getAuthId();
        this.courseReviewId = entity.getCourseReviewId();
    }

}
