package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.model.Follow;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

import java.util.List;

public interface FollowService {

    void createFollow(CreatePopularitiesRequest request);
    void deleteFollow(Long id);
    List<Follow> readMyFollowers();
    List<Follow> readMyFollowings();


    default FollowEntity followdtoToEntity(CreatePopularitiesRequest request) {
        FollowEntity followEntity = FollowEntity.builder()
            .authId(request.getAuthId())
            .followingId(request.getFollowingId())
            .build();
        return followEntity;
    }
}
