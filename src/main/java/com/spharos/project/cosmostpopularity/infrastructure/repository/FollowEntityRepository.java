package com.spharos.project.cosmostpopularity.infrastructure.repository;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowEntityRepository extends JpaRepository<FollowEntity, Long> {

    Slice<FollowEntity> findAllByAuthId(Long id, Pageable pageable);
    Slice<FollowEntity> findAllByFollowingId(Long id, Pageable pageable);

    List<FollowEntity> findByAuthIdAndFollowingId(Long authId, Long followingId);
}
