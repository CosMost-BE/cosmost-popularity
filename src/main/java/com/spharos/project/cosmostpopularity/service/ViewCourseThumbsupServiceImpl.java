package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseThumbsupRepository;
import com.spharos.project.cosmostpopularity.responsebody.ReadCourseThumbsupSortAllResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ViewCourseThumbsupServiceImpl implements ViewCourseThumbsupService {

    private final CourseThumbsupRepository courseThumbsupRepository;

    @Autowired
    public ViewCourseThumbsupServiceImpl(CourseThumbsupRepository courseThumbsupRepository) {
        this.courseThumbsupRepository = courseThumbsupRepository;
    }


    @Override
    public List<ReadCourseThumbsupSortAllResponse> readCourseThumbsupSortAll(Pageable pageable) {

        Slice<CourseThumbsupEntity> courseThumbsupSlice = courseThumbsupRepository.CourseThumbsupSort(pageable);
        List<Float> courseThumbsupCountList = courseThumbsupRepository.CourseThumbsupCount(pageable);
        List<ReadCourseThumbsupSortAllResponse> courseThumbsupSortList = new ArrayList<>();

        for(int i=0; i<courseThumbsupSlice.getContent().size(); i++) {
            courseThumbsupSortList.add(ReadCourseThumbsupSortAllResponse.builder()
                    .courseId(courseThumbsupSlice.getContent().get(i).getCourseId())
                    .courseThumbsCnt(courseThumbsupCountList.get(i).longValue())
                    .whetherLastPage(courseThumbsupSlice.isLast())
                    .build());
        }

        return courseThumbsupSortList;
    }

}
