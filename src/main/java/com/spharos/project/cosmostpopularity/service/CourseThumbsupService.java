package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.model.CourseThumbsup;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;

import java.util.List;

public interface CourseThumbsupService {

    CourseThumbsupEntity createCourseThumbsup(CreatePopularitiesRequest request);
    void deleteCourseThumbsup(Long id);
    List<CourseThumbsupView> readThumbsupByMe(Long id);
    CourseThumbsupCountView readCourseThumbsupCount(Long id);
    List<CourseThumbsupView> readAllThumbsupByMe();

}
