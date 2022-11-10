package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.model.CourseReviewThumbsup;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseReviewThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseReviewThumbsupView;

import java.util.List;

public interface CourseReviewThumbsupService {

    CourseReviewThumbsup createCourseReviewThumbsup(CreatePopularitiesRequest request);
    void deleteCourseReviewThumbsup(Long id);
    List<CourseReviewThumbsupView> readCourseReviewThumbsupByMe(Long id);

    CourseReviewThumbsupCountView readCourseReviewThumbsupCount(Long id);
}
