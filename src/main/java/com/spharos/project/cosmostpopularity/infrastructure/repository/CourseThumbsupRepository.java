package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseThumbsupRepository extends JpaRepository<CourseThumbsupEntity, Long> {

    List<CourseThumbsupEntity> findByAuthIdAndCourseId(Long authId, Long courseId);
    Long countByCourseId(Long id);
    List<CourseThumbsupEntity> findAllByAuthId(Long id);
}
