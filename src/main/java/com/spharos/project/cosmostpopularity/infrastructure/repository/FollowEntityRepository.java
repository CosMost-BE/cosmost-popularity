package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowEntityRepository extends JpaRepository<FollowEntity, Long> {

    List<FollowEntity> findAllByAuthId(Long id);
    List<FollowEntity> findAllByFollowingId(Long id);

    List<FollowEntity> findByAuthIdAndFollowingId(Long authId, Long followingId);
}
