package com.spharos.project.cosmostpopularity.model;

import com.spharos.project.cosmostpopularity.infrastructure.entity.FollowEntity;
import lombok.Getter;
import lombok.ToString;


@Getter
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
