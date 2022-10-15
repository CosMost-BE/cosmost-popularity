package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

public interface CourseReviewThumbsupService {

    void createCourseReviewThumbsup(CreatePopularitiesRequest request);
    void deleteCourseReviewThumbsup(Long id);




    default CourseReviewThumbsupEntity reviewThumbsupdtoToEntity(CreatePopularitiesRequest request) {

        CourseReviewThumbsupEntity courseReviewThumbsupEntity = CourseReviewThumbsupEntity.builder()
                .courseId(request.getCourseId())
                .authId(request.getAuthId())
                .build();
        return courseReviewThumbsupEntity;
    }



}
