package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseReviewThumbsupNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseReviewThumbsupEntityRepository;
import com.spharos.project.cosmostpopularity.model.CourseReviewThumbsup;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
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
}