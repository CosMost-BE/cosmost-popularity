package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.AuthIdNotFoundException;
import com.spharos.project.cosmostpopularity.exception.FollowIdNotFoundException;
import com.spharos.project.cosmostpopularity.exception.FollowingIdNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.FollowEntityRepository;
import com.spharos.project.cosmostpopularity.model.Follow;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.responsebody.ReadFollowEntityResponse;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FollowServiceImpl implements FollowService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final FollowEntityRepository followEntityRepository;

    @Autowired
    public FollowServiceImpl(FollowEntityRepository followEntityRepository) {
        this.followEntityRepository = followEntityRepository;
    }

    // 팔로워 등록하기
    @Override
    public Follow createFollow(CreatePopularitiesRequest createPopularitiesRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        FollowEntity followEntity = FollowEntity.builder()
                .authId(authId)
                .followingId(createPopularitiesRequest.getFollowingId())
                .build();

        followEntityRepository.save(followEntity);

        return Follow.builder()
                .id(followEntity.getId())
                .authId(followEntity.getAuthId())
                .followingId(followEntity.getFollowingId())
                .build();
    }

    // 언팔로우(팔로잉 취소)
    @Override
    public void deleteFollow(Long followingId) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        try {
            Optional<List<FollowEntity>> followEntityId = Optional.ofNullable(
                    Optional.ofNullable(followEntityRepository.findByAuthIdAndFollowingId(authId, followingId)).orElseThrow(FollowIdNotFoundException::new));

            if (followEntityId.isPresent()) {
                followEntityRepository.deleteById(followEntityId.get().get(0).getId());
            }

        } catch (Exception e) {
            throw new FollowingIdNotFoundException();
        }

    }

    // 나의 팔로워 조회하기 followingId(주인)
    @Override
    public List<ReadFollowEntityResponse> readMyFollowers(Pageable pageable) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long followingId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Slice<FollowEntity> followEntityList = followEntityRepository.findAllByFollowingId(followingId, pageable);

        if (!followEntityList.isEmpty()) {
            List<ReadFollowEntityResponse> readFollowEntityResponseList = new ArrayList<>();

            followEntityList.forEach(followEntity -> {
                readFollowEntityResponseList.add(ReadFollowEntityResponse.builder()
                        .id(followEntity.getId())
                        .authId(followEntity.getAuthId())
                        .followingId(followEntity.getFollowingId())
                        .whetherLastPage(followEntityList.isLast())
                        .build());
            });

            return readFollowEntityResponseList;
        }
        throw new AuthIdNotFoundException();
    }

    // 나의 팔로잉 조회하기 (authId가 주인)
    @Override
    public List<ReadFollowEntityResponse> readMyFollowings(Pageable pageable) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Slice<FollowEntity> followEntityList = followEntityRepository.findAllByAuthId(authId, pageable);

        if (!followEntityList.isEmpty()) {

            List<ReadFollowEntityResponse> readFollowEntityResponseList = new ArrayList<>();

            followEntityList.forEach(followEntity -> {
                readFollowEntityResponseList.add(ReadFollowEntityResponse.builder()
                        .id(followEntity.getId())
                        .authId(followEntity.getAuthId())
                        .followingId(followEntity.getFollowingId())
                        .whetherLastPage(followEntityList.isLast())
                        .build());
            });

            return readFollowEntityResponseList;


//            return followEntityList.stream().map(followEntity ->
//                    new Follow(followEntity)).collect(Collectors.toList());
        }
        throw new FollowingIdNotFoundException();
    }

    @Override
    public List<ReadFollowEntityResponse> readOtherUserFollowers(Pageable pageable) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        Long following = Long.valueOf(request.getHeader("Authorization"));

        Slice<FollowEntity> followingId = followEntityRepository.findAllByFollowingId(following, pageable);

        if (!followingId.isEmpty()) {

            List<ReadFollowEntityResponse> readFollowEntityResponseList = new ArrayList<>();

            followingId.forEach(followEntity -> {
                readFollowEntityResponseList.add(ReadFollowEntityResponse.builder()
                        .id(followEntity.getId())
                        .authId(followEntity.getAuthId())
                        .followingId(followEntity.getFollowingId())
                        .whetherLastPage(followingId.isLast())
                        .build());
            });

            return readFollowEntityResponseList;


//            return followingId.stream().map(followEntity ->
//                    new Follow(followEntity)).collect(Collectors.toList());
        }
        throw new AuthIdNotFoundException();

    }
}
