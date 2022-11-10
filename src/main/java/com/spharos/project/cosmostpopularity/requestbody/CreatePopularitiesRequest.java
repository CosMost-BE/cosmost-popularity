package com.spharos.project.cosmostpopularity.requestbody;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePopularitiesRequest {

    private Long id;
    private Long authId;
    private Long courseId;
    private Long courseReviewId;
    private String type;
    private Long followingId;

    public CourseThumbsupEntity createDtoToEntity() {
        return CourseThumbsupEntity.builder()
                .id(id)
                .authId(authId)
                .courseId(courseId)
                .build();
    }

}
