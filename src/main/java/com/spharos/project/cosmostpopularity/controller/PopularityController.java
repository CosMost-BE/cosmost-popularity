package com.spharos.project.cosmostpopularity.controller;

import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.service.CourseReviewThumbsupService;
import com.spharos.project.cosmostpopularity.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/popularities")
@Slf4j
public class PopularityController {

    private final CourseReviewThumbsupService courseReviewThumbsupService;
    private final FollowService followService;

    @Autowired
    public PopularityController(CourseReviewThumbsupService courseReviewThumbsupService,
                                          FollowService followService) {
        this.courseReviewThumbsupService = courseReviewThumbsupService;
        this.followService = followService;
    }

    // 코스리뷰 좋아요 등록
    // 팔로우 등록
    @PostMapping("")
    public ResponseEntity<String> createPopularities(@RequestBody CreatePopularitiesRequest request){

        if(request.getType().equals("courseReviewThumbsup")) {
            courseReviewThumbsupService.createCourseReviewThumbsup(request);
            return ResponseEntity.ok("코스리뷰 좋아요 성공!!");
        } else if(request.getType().equals("follow")) {
            followService.createFollow(request);
            return ResponseEntity.ok("팔로워 등록 성공!!");
        }

        return null;
    }

    @DeleteMapping("/{id}/review")
    public ResponseEntity<String> deleteCourseReviewThumbsup(@PathVariable Long id){
            courseReviewThumbsupService.deleteCourseReviewThumbsup(id);
        return ResponseEntity.ok("코스리뷰 좋아요가 취소 되었습니다.");
    }

}
