package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

public interface FollowService {

    void createFollow(CreatePopularitiesRequest request);
    void deleteFollow(Long id);

    default FollowEntity followdtoToEntity(CreatePopularitiesRequest request) {
        FollowEntity followEntity = FollowEntity.builder()
            .authId(request.getAuthId())
            .followingId(request.getFollowingId())
            .build();
        return followEntity;
    }
}
