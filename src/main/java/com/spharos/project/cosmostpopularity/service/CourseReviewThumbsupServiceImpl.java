package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseReviewThumbsupNotFoundException;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseReviewThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseReviewThumbsupEntityRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import java.util.Optional;

import io.jsonwebtoken.Jwts;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public void createCourseReviewThumbsup(CreatePopularitiesRequest createPopularitiesRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long id = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());


        CourseReviewThumbsupEntity courseReviewThumbsupEntity = reviewThumbsupdtoToEntity(createPopularitiesRequest);
        courseReviewThumbsupRepository.save(courseReviewThumbsupEntity);
    }

    @Override
    public void deleteCourseReviewThumbsup(Long id) {

        Optional<CourseReviewThumbsupEntity> thumbsupId =
            Optional.of(courseReviewThumbsupRepository.findById(id)
                .orElseThrow(CourseReviewThumbsupNotFoundException::new));

        if (thumbsupId.isPresent()) {
            courseReviewThumbsupRepository.deleteById(id);
        }
    }



}