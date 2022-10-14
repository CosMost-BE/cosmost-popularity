package com.spharos.project.cosmostpopularity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/popularities")
public class CourseReviewThumbsupController {

    @PostMapping("")
    public ResponseEntity<?> createCourseReviewThumbsup(){

        return null;
    }

}