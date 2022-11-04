package com.spharos.project.cosmostpopularity.responsebody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadAllThumbsupByMeResponse {

    private Long id;
    private Long courseId;

    private boolean whetherLastPage;

}
