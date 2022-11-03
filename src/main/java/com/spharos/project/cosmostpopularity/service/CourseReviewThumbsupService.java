package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.model.CourseReviewThumbsup;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

public interface CourseReviewThumbsupService {

    CourseReviewThumbsup createCourseReviewThumbsup(CreatePopularitiesRequest request);
    void deleteCourseReviewThumbsup(Long id);



}
