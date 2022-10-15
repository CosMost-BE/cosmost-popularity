package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

public interface FollowService {

    void createFollow(CreatePopularitiesRequest request);

    default FollowEntity followdtoToEntity(CreatePopularitiesRequest request) {
        FollowEntity followEntity = FollowEntity.builder()
            .authId(request.getAuthId())
            .followingId(request.getFollowingId())
            .build();
        return followEntity;
    }
}
