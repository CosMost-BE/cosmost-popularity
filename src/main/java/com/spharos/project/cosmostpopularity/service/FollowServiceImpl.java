package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.FollowEntityRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowEntityRepository followEntityRepository;

    @Autowired
    public FollowServiceImpl(FollowEntityRepository followEntityRepository) {
        this.followEntityRepository = followEntityRepository;
    }

    @Override
    public void createFollow(CreatePopularitiesRequest request) {
        FollowEntity followEntity = followdtoToEntity(request);
        followEntityRepository.save(followEntity);
    }
}
