package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseReviewThumbsupNotFoundException;
import com.spharos.project.cosmostpopularity.exception.DuplicateCourseThumbsup;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseReviewThumbsupEntityRepository;
import com.spharos.project.cosmostpopularity.model.CourseReviewThumbsup;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseReviewThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseReviewThumbsupView;
import io.jsonwebtoken.Jwts;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@ToString
public class CourseReviewThumbsupServiceImpl implements CourseReviewThumbsupService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final CourseReviewThumbsupEntityRepository courseReviewThumbsupRepository;

    @Autowired
    public CourseReviewThumbsupServiceImpl(
            CourseReviewThumbsupEntityRepository courseReviewThumbsupRepository) {
        this.courseReviewThumbsupRepository = courseReviewThumbsupRepository;
    }

    // 코스리뷰 좋아요
    @Override
    public CourseReviewThumbsup createCourseReviewThumbsup(CreatePopularitiesRequest createPopularitiesRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long id = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        CourseReviewThumbsupEntity courseReviewThumbsupEntity = CourseReviewThumbsupEntity.builder()
                .authId(id)
                .courseReviewId(createPopularitiesRequest.getCourseReviewId())
                .build();

        courseReviewThumbsupRepository.save(courseReviewThumbsupEntity);

        return CourseReviewThumbsup.builder()
                .id(courseReviewThumbsupEntity.getId())
                .authId(id)
                .courseReviewId(courseReviewThumbsupEntity.getCourseReviewId())
                .build();
    }

    // 코스 리뷰 좋아요 취소
    @Override
    public void deleteCourseReviewThumbsup(Long id) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Optional<List<CourseReviewThumbsupEntity>> courseReviewThumbsupId =
                Optional.ofNullable(Optional.ofNullable(courseReviewThumbsupRepository.findByAuthIdAndCourseReviewId(authId, id))
                        .orElseThrow(CourseReviewThumbsupNotFoundException::new));

        try {
            if (courseReviewThumbsupId.isPresent()) {
                courseReviewThumbsupRepository.deleteById(courseReviewThumbsupId.get().get(0).getId());
            }
        } catch (Exception e) {
            throw new CourseReviewThumbsupNotFoundException();
        }

    }

    @Override
    public List<CourseReviewThumbsupView> readCourseReviewThumbsupByMe(Long courseReviewId) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Optional<List<CourseReviewThumbsupEntity>> courseThumbsupEntityList =
                Optional.of(Optional.ofNullable(courseReviewThumbsupRepository.findByAuthIdAndCourseReviewId(authId, courseReviewId))
                        .orElseThrow(DuplicateCourseThumbsup::new));


        return  courseThumbsupEntityList.get().stream().map(courseReviewThumbsup ->
                new CourseReviewThumbsupView(courseReviewThumbsup)).collect(Collectors.toList());
    }

    // 코스리뷰 좋아요 개수 조회
    @Override
    public CourseReviewThumbsupCountView readCourseReviewThumbsupCount(Long id) {

        Long courseReviewThumbsupCnt = courseReviewThumbsupRepository.countByCourseReviewId(id);

        return CourseReviewThumbsupCountView.builder()
                .courseReviewThumbsupCount(courseReviewThumbsupCnt)
                .build();
    }
}