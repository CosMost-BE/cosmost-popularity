package com.spharos.project.cosmostpopularity.service;


import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.responsebody.ReadAllThumbsupByMeResponse;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseThumbsupService {

    CourseThumbsupEntity createCourseThumbsup(CreatePopularitiesRequest request);
    void deleteCourseThumbsup(Long id);
    List<CourseThumbsupView> readCourseThumbsupByMe(Long id);
    CourseThumbsupCountView readCourseThumbsupCount(Long id);
    List<ReadAllThumbsupByMeResponse> readAllThumbsupByMe(Pageable pageable);

}
