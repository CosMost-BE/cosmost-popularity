package com.spharos.project.cosmostpopularity.model;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class CourseThumbsup {

    private Long id;
    private Long authId;
    private Long courseId;


    public CourseThumbsup(CourseThumbsupEntity entity) {
        this.authId = entity.getAuthId();
        this.courseId = entity.getCourseId();
    }

}
