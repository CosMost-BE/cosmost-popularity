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
public class ReadOtherUserFollowersCntResponse {

    private Long otherUserFollowerCnt;
    List<ReadFollowEntityResponse> readFollowEntityResponseList;
}
