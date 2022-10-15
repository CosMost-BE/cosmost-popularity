package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

public interface CourseThumbsupService {

    void createCourseThumbsup(CreatePopularitiesRequest request);

    default CourseThumbsupEntity courseThumbsupdtoToEntity(CreatePopularitiesRequest request) {

        CourseThumbsupEntity courseThumbsupEntity = CourseThumbsupEntity.builder()
            .authId(request.getAuthId())
            .courseId(request.getCourseId())
            .build();

        return courseThumbsupEntity;
    }
}
