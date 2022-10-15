package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowEntityRepository extends JpaRepository<FollowEntity, Long> {

}
