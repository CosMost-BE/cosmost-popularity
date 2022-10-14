package com.spharos.project.cosmostpopularity.controller;

import com.spharos.project.cosmostpopularity.requestbody.CreateCourseReviewThumbsupRequest;
import com.spharos.project.cosmostpopularity.service.CourseReviewThumbsupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/popularities")
@Slf4j
public class CourseReviewThumbsupController {

    private final CourseReviewThumbsupService courseReviewThumbsupService;

    @Autowired
    public CourseReviewThumbsupController(CourseReviewThumbsupService courseReviewThumbsupService) {
        this.courseReviewThumbsupService = courseReviewThumbsupService;
    }

    //코스리뷰 좋아요 등록
    @PostMapping("")
    public ResponseEntity<String> createCourseReviewThumbsup(CreateCourseReviewThumbsupRequest request){
        log.info(String.valueOf(request.getAuthId()));
        log.info(String.valueOf(request.getCourseId()));
        courseReviewThumbsupService.createCourseReviewThumbsup(request);
        return ResponseEntity.ok("코스리뷰 좋아요 성공!!");

    }

}
