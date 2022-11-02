package com.spharos.project.cosmostpopularity.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseThumbsupView {

    private Long authId;
    private Long courseId;

}
