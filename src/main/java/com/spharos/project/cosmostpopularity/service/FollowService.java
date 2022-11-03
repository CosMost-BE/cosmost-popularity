package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.model.Follow;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;

import java.util.List;

public interface FollowService {

    Follow createFollow(CreatePopularitiesRequest request);
    void deleteFollow(Long id);
    List<Follow> readMyFollowers();
    List<Follow> readMyFollowings();

    List<Follow> readOtherUserFollowers();
}
