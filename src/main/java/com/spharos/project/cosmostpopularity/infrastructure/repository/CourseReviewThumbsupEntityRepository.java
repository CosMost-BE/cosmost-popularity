package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewThumbsupEntityRepository extends JpaRepository<CourseReviewThumbsupEntity, Long> {

    List<CourseReviewThumbsupEntity> findByAuthIdAndCourseReviewId(Long authId, Long courseReviewId);
    Long countByCourseReviewId(Long id);

}
