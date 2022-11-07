package com.spharos.project.cosmostpopularity.responsebody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadMyFollowingsCntResponse {

    private Long readMyFollowingCnt;
    List<ReadFollowEntityResponse> readFollowingEntityResponseList;
}
