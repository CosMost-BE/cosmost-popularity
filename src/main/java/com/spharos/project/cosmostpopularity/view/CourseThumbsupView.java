package com.spharos.project.cosmostpopularity.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseThumbsupView {

    private Long authId;
    private Long courseId;


    public CourseThumbsupView(CourseThumbsupEntity courseThumbsup) {
        this.authId = courseThumbsup.getAuthId();
        this.courseId = courseThumbsup.getCourseId();
    }

}
