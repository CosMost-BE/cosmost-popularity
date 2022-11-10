package com.spharos.project.cosmostpopularity.model;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@AllArgsConstructor
@ToString
public class Follow {

    private Long id;
    private Long authId;
    private Long followingId;


    public Follow(FollowEntity entity) {
        this.id = entity.getId();
        this.authId = entity.getAuthId();
        this.followingId = entity.getFollowingId();
    }

}
