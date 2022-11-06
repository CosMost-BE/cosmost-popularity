package com.spharos.project.cosmostpopularity.controller;

import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.service.CourseReviewThumbsupService;
import com.spharos.project.cosmostpopularity.service.CourseThumbsupService;
import com.spharos.project.cosmostpopularity.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/popularities")
@Slf4j
public class PopularityController {

    private final CourseReviewThumbsupService courseReviewThumbsupService;
    private final FollowService followService;
    private final CourseThumbsupService courseThumbsupService;

    @Autowired
    public PopularityController(CourseReviewThumbsupService courseReviewThumbsupService,
                                FollowService followService,
                                CourseThumbsupService courseThumbsupService) {
        this.courseReviewThumbsupService = courseReviewThumbsupService;
        this.followService = followService;
        this.courseThumbsupService = courseThumbsupService;
    }

    // 코스리뷰 좋아요 등록
    // 팔로우 등록
    @PostMapping("")
    public ResponseEntity<String> createPopularities(@RequestBody CreatePopularitiesRequest request) {

        if (request.getType().equals("courseReviewThumbsup")) {
            courseReviewThumbsupService.createCourseReviewThumbsup(request);
            return ResponseEntity.ok("코스리뷰 좋아요 성공!!!");
        } else if (request.getType().equals("follow")) {
            followService.createFollow(request);
            return ResponseEntity.ok("팔로잉 등록 성공!!!");
        } else if (request.getType().equals("course")) {
            courseThumbsupService.createCourseThumbsup(request);
            return ResponseEntity.ok("코스 좋아요 성공 !!!");
        }
        return null;
    }

    // 마이페이지에서 나의 팔로워 조회
    // 내가 누른 코스 좋아요 전체조회
    @GetMapping("")
    public ResponseEntity<?> readMyFollowers(@RequestParam(value = "filter", defaultValue = " ", required = false) String filter,
                                             @RequestParam(value = "type", defaultValue = " ", required = false) String type,
                                             Pageable pageable
    ) {

        if (String.valueOf(filter).equals("auth") && String.valueOf(type).equals("follower")) {
            return ResponseEntity.ok().body(followService.readMyFollowers(pageable));
        } else if (String.valueOf(filter).equals("auth") && String.valueOf(type).equals("following")) {
            return ResponseEntity.ok().body(followService.readMyFollowings(pageable));
        } else if (String.valueOf(type).equals("cosmost")) {
            return ResponseEntity.ok().body(courseThumbsupService.readAllThumbsupByMe(pageable));
        } else if (filter.equals("cosmosts") && type.equals("follower")){
            return ResponseEntity.ok().body(followService.readOtherUserFollowers(pageable));
        }
            return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> readCourseThumbsup(@RequestParam(value = "type", required = false) String type,
                                                @RequestParam(value = "filter", required = false) String filter,
                                                @PathVariable(value = "id") Long id) {

        // 코스의 좋아요 개수 조회
        if (String.valueOf(filter).equals("count") && type.equals("cosmost")) {
            return ResponseEntity.ok().body(courseThumbsupService.readCourseThumbsupCount(id));

        } else if(String.valueOf(filter).equals("count") && type.equals("review")){
            return ResponseEntity.ok().body(courseReviewThumbsupService.readCourseReviewThumbsupCount(id));
        // 내가 누른 해당 코스 좋아요
        } else if (type.equals("cosmost")) {
            return ResponseEntity.ok().body(courseThumbsupService.readCourseThumbsupByMe(id));
        // 내가 누른 해당 코스리뷰 좋아요
        } else if (type.equals("review")) {
            return ResponseEntity.ok().body(courseReviewThumbsupService.readCourseReviewThumbsupByMe(id));
        } else if (type.equals("follow")) {
            return ResponseEntity.ok().body(followService.readMyFollowerByMe(id));
        }
        return null;
    }

    @DeleteMapping("/{id}/review")
    public ResponseEntity<String> deleteCourseReviewThumbsup(@PathVariable Long id) {
        courseReviewThumbsupService.deleteCourseReviewThumbsup(id);
        return ResponseEntity.ok("코스리뷰 좋아요가 취소 되었습니다.");
    }


    @DeleteMapping("/{id}/cosmost")
    public ResponseEntity<String> deleteCourseThumbsup(@PathVariable Long id) {
        courseThumbsupService.deleteCourseThumbsup(id);
        return ResponseEntity.ok("코스 좋아요가 취소 되었습니다.");
    }

    @DeleteMapping("/{id}/following")
    public ResponseEntity<String> deletefollow(@PathVariable Long id) {
        followService.deleteFollow(id);
        return ResponseEntity.ok("팔로잉이 취소 되었습니다.");
    }
}
