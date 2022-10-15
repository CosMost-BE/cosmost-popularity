package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreateCourseReviewThumbsupRequest;

public interface CourseReviewThumbsupService {

    void createCourseReviewThumbsup(CreateCourseReviewThumbsupRequest request);

    default CourseReviewThumbsupEntity dtoToEntity(CreateCourseReviewThumbsupRequest request) {

        CourseReviewThumbsupEntity courseReviewThumbsupEntity = CourseReviewThumbsupEntity.builder()
                .courseId(request.getCourseId())
                .authId(request.getAuthId())
                .build();

        return courseReviewThumbsupEntity;
    }

}
