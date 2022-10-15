package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseThumbsupRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseThumbsupServiceImpl implements CourseThumbsupService {

    private final CourseThumbsupRepository courseThumbsupRepository;

    @Autowired
    public CourseThumbsupServiceImpl(CourseThumbsupRepository courseThumbsupRepository) {
        this.courseThumbsupRepository = courseThumbsupRepository;
    }

    @Override
    public void createCourseThumbsup(CreatePopularitiesRequest request) {
        CourseThumbsupEntity courseThumbsupEntity = courseThumbsupdtoToEntity(request);
        courseThumbsupRepository.save(courseThumbsupEntity);
    }
}
