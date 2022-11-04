package com.spharos.project.cosmostpopularity.controller;


import com.spharos.project.cosmostpopularity.exception.CourseParamNotFoundException;
import com.spharos.project.cosmostpopularity.responsebody.ReadCourseThumbsupSortAllResponse;
import com.spharos.project.cosmostpopularity.service.ViewCourseThumbsupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1")
@Slf4j
public class ViewController {

    private final ViewCourseThumbsupService viewCourseThumbsupService;

    @Autowired
    public ViewController(ViewCourseThumbsupService viewCourseThumbsupService) {
        this.viewCourseThumbsupService = viewCourseThumbsupService;
    }

    @GetMapping("/view/ranking")
    public ResponseEntity<List<ReadCourseThumbsupSortAllResponse>> ReadCourseThumbsupSortAll(@RequestParam(value = "order", defaultValue = " ", required = false) String order,
                                                                                                   Pageable pageable) {
        if(order.equals("popularity")) {
            return ResponseEntity.ok().body(viewCourseThumbsupService.readCourseThumbsupSortAll(pageable));
        }

        throw new CourseParamNotFoundException();
    }


}
