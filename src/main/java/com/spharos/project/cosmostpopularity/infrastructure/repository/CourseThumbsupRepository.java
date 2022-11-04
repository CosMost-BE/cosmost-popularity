package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseThumbsupRepository extends JpaRepository<CourseThumbsupEntity, Long> {

    List<CourseThumbsupEntity> findByAuthIdAndCourseId(Long authId, Long courseId);
    Long countByCourseId(Long id);
    List<CourseThumbsupEntity> findAllByAuthId(Long id);

    @Query(value = "select c from CourseThumbsupEntity c " +
            "group by c.courseId order by count(c.courseId) desc")
    Slice<CourseThumbsupEntity> CourseThumbsupSort(Pageable pageable);

    @Query(value = "select count(c.courseId) from CourseThumbsupEntity c " +
            "group by c.courseId order by count(c.courseId) desc")
    List<Float> CourseThumbsupCount(Pageable pageable);
}
