package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.responsebody.ReadCourseThumbsupSortAllResponse;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViewCourseThumbsupService {

    List<ReadCourseThumbsupSortAllResponse> readCourseThumbsupSortAll(Pageable pageable);

}
