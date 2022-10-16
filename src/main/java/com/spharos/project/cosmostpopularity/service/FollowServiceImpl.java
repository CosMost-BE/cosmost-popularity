package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.FollowIdNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.FollowEntityRepository;
import com.spharos.project.cosmostpopularity.model.Follow;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public void deleteFollow(Long id) {

        Optional<FollowEntity> followEntityId = Optional.ofNullable(followEntityRepository.findById(id)
                .orElseThrow(FollowIdNotFoundException::new));

        if(followEntityId.isPresent()) {
            followEntityRepository.deleteById(id);
        }
    }

    @Override
    public List<Follow> readMyFollowers() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long id = Long.parseLong(request.getHeader("Authorization"));

        List<FollowEntity> followEntityList = followEntityRepository.findAllByAuthId(id);

        return followEntityList.stream().map(followEntity ->
                new Follow(followEntity)).collect(Collectors.toList());
    }
}
