package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseIdNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseThumbsupRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseThumbsupServiceImpl implements CourseThumbsupService {

    private final CourseThumbsupRepository courseThumbsupRepository;

    @Autowired
    public CourseThumbsupServiceImpl(CourseThumbsupRepository courseThumbsupRepository) {
        this.courseThumbsupRepository = courseThumbsupRepository;
    }

    @Override
    public void createCourseThumbsup(CreatePopularitiesRequest request) {
        CourseThumbsupEntity courseThumbsupEntity = courseThumbsupdtoToEntity(request);
        courseThumbsupRepository.save(courseThumbsupEntity);
    }

    @Override
    public void deleteCourseThumbsup(Long id) {
        Optional<CourseThumbsupEntity> courseThumbsupId = Optional.ofNullable(courseThumbsupRepository.findById(id)
                .orElseThrow(CourseIdNotFoundException::new));

        if (courseThumbsupId.isPresent()) {
            courseThumbsupRepository.deleteById(id);
        }
    }

    // 내가 누른 해당 코스 좋아요
    @Override
    public List<CourseThumbsupView> readThumbsupByMe(Long id) {

        List<CourseThumbsupEntity> courseThumbsupEntityList = courseThumbsupRepository.findByAuthId(id);
        List<CourseThumbsupView> courseThumbsupViews = new ArrayList<>();

        for (CourseThumbsupEntity reportCategoryEntity : courseThumbsupEntityList) {
            courseThumbsupViews.add(CourseThumbsupView.builder()
                    .authId(reportCategoryEntity.getAuthId())
                    .courseId(reportCategoryEntity.getCourseId())
                    .build());
        }

        return courseThumbsupViews;
    }
    
    // 코스의 좋아요 개수 조회
    @Override
    public CourseThumbsupCountView readCourseThumbsupCount(Long id) {
        Long courseThumbsupCnt = courseThumbsupRepository.countByCourseId(id);

        return CourseThumbsupCountView.builder()
                .courseThumbsCnt(courseThumbsupCnt)
                .build();
    }
}
