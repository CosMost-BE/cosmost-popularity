package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseReviewThumbsupNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseReviewThumbsupRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreateCourseReviewThumbsupRequest;
import java.util.Optional;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ToString
public class CourseReviewThumbsupServiceImpl implements CourseReviewThumbsupService {

    private final CourseReviewThumbsupRepository courseReviewThumbsupRepository;

    @Autowired
    public CourseReviewThumbsupServiceImpl(
        CourseReviewThumbsupRepository courseReviewThumbsupRepository) {
        this.courseReviewThumbsupRepository = courseReviewThumbsupRepository;
    }

    @Override
    public void createCourseReviewThumbsup(CreateCourseReviewThumbsupRequest request) {
        CourseReviewThumbsupEntity courseReviewThumbsupEntity = dtoToEntity(request);
        courseReviewThumbsupRepository.save(courseReviewThumbsupEntity);
    }

    @Override
    public void deleteCourseReviewThumbsup(Long id) {

        try {
            Optional<CourseReviewThumbsupEntity> thumbsupId =
                Optional.of(courseReviewThumbsupRepository.findById(id).orElseThrow(
                CourseReviewThumbsupNotFoundException::new));
            if (thumbsupId.isPresent()) {
                courseReviewThumbsupRepository.deleteById(id);
            }
        } catch (CourseReviewThumbsupNotFoundException e) {
            e.printStackTrace();
        }

    }
}