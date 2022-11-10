package com.spharos.project.cosmostpopularity.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spharos.project.cosmostpopularity.model.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowView {

    private Long id;
    private Long authId;
    private Long followingId;

    public FollowView(Follow follow) {
        this.id = follow.getId();
        this.authId = follow.getAuthId();
        this.followingId = follow.getFollowingId();
    }


}
