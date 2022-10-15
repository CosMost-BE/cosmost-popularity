package com.spharos.project.cosmostpopularity.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseReviewThumbsupRequest {

    private Long authId;
    private Long courseId;

}
