package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.model.Follow;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.responsebody.ReadFollowEntityResponse;
import com.spharos.project.cosmostpopularity.responsebody.ReadOtherUserFollowersCntResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {

    Follow createFollow(CreatePopularitiesRequest request);
    void deleteFollow(Long id);
    List<ReadFollowEntityResponse> readMyFollowers(Pageable pageable);
    List<ReadFollowEntityResponse> readMyFollowings(Pageable pageable);
    List<ReadOtherUserFollowersCntResponse> readOtherUserFollowers(Pageable pageable);

    List<Follow> readMyFollowerByMe(Long id);
}
