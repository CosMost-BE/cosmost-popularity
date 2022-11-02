package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseThumbsupRepository extends JpaRepository<CourseThumbsupEntity, Long> {

    List<CourseThumbsupEntity> findByAuthId(Long id);
}
