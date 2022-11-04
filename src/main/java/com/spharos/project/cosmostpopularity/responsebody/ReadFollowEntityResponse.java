package com.spharos.project.cosmostpopularity.responsebody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadFollowEntityResponse {

    private Long id;
    private Long authId;
    private Long followingId;

    private boolean whetherLastPage;

}
