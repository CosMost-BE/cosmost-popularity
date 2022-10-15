package com.spharos.project.cosmostpopularity.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePopularitiesRequest {

    private Long authId;
    private Long courseId;
    private String type;
    private Long followingId;

}
