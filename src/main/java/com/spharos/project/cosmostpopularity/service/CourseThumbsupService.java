package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;

import java.util.List;

public interface CourseThumbsupService {

    void createCourseThumbsup(CreatePopularitiesRequest request);
    void deleteCourseThumbsup(Long id);
    List<CourseThumbsupView> readThumbsupByMe(Long id);

    default CourseThumbsupEntity courseThumbsupdtoToEntity(CreatePopularitiesRequest request) {

        CourseThumbsupEntity courseThumbsupEntity = CourseThumbsupEntity.builder()
            .authId(request.getAuthId())
            .courseId(request.getCourseId())
            .build();

        return courseThumbsupEntity;
    }
}
