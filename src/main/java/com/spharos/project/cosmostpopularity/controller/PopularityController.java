package com.spharos.project.cosmostpopularity.controller;

import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.service.CourseReviewThumbsupService;
import com.spharos.project.cosmostpopularity.service.CourseThumbsupService;
import com.spharos.project.cosmostpopularity.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.ok("팔로워 등록 성공!!!");
        } else if (request.getType().equals("course")) {
            courseThumbsupService.createCourseThumbsup(request);
            return ResponseEntity.ok("코스 좋아요 성공 !!!");
        }
        return null;
    }

    // 마이페이지에서 팔로워 조회
    // 내가 누른 해당 코스 좋아요
    @GetMapping("/")
    public ResponseEntity<?> readMyFollowers(@RequestParam(value = "filter") String filter,
                                             @RequestParam(value = "type") String type) {

        if (filter.equals("auth") && type.equals("follower")) {
            return ResponseEntity.ok().body(followService.readMyFollowers());
        } else if (filter.equals("auth") && type.equals("following")) {
            return ResponseEntity.ok().body(followService.readMyFollowings());
        }
        return null;
    }

    // 내가 누른 해당 코스 좋아요
    // 코스의 좋아요 개수 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> readCourseThumbsup(@RequestParam(value = "type", required = false) String type,
                                                @RequestParam(value = "filter", required = false) String filter,
                                                @PathVariable(value = "id") Long id) {

        if (String.valueOf(filter).equals("count") && type.equals("cosmost")) {
            return ResponseEntity.ok().body(courseThumbsupService.readCourseThumbsupCount(id));
        } else  if (type.equals("cosmost"))   {
            return ResponseEntity.ok().body(courseThumbsupService.readThumbsupByMe(id));
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
        return ResponseEntity.ok("팔로우가 취소 되었습니다.");
    }
}
