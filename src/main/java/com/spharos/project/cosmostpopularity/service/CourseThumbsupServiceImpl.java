package com.spharos.project.cosmostpopularity.service;

import com.spharos.project.cosmostpopularity.exception.CourseIdNotFoundException;
import com.spharos.project.cosmostpopularity.exception.DuplicateCourseThumbsup;
import com.spharos.project.cosmostpopularity.infrastructure.entity.CourseThumbsupEntity;
import com.spharos.project.cosmostpopularity.infrastructure.repository.CourseThumbsupRepository;
import com.spharos.project.cosmostpopularity.requestbody.CreatePopularitiesRequest;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupCountView;
import com.spharos.project.cosmostpopularity.view.CourseThumbsupView;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseThumbsupServiceImpl implements CourseThumbsupService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final CourseThumbsupRepository courseThumbsupRepository;

    @Autowired
    public CourseThumbsupServiceImpl(CourseThumbsupRepository courseThumbsupRepository) {
        this.courseThumbsupRepository = courseThumbsupRepository;
    }

    // 코스 좋아요 등록
    @Override
    public CourseThumbsupEntity createCourseThumbsup(CreatePopularitiesRequest createPopularitiesRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long id = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        CourseThumbsupEntity courseThumbsupEntity = CourseThumbsupEntity.builder()
                .authId(id)
                .courseId(createPopularitiesRequest.getCourseId())
                .build();

        courseThumbsupRepository.save(courseThumbsupEntity);

        return CourseThumbsupEntity.builder()
                .id(courseThumbsupEntity.getId())
                .authId(courseThumbsupEntity.getAuthId())
                .courseId(courseThumbsupEntity.getCourseId())
                .build();
    }

    // 코스 좋아요 취소
    @Override
    public void deleteCourseThumbsup(Long id) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());


        Optional<List<CourseThumbsupEntity>> courseThumbsupId = Optional.ofNullable(Optional.ofNullable(
                courseThumbsupRepository.findByAuthIdAndCourseId(authId, id)).orElseThrow(CourseIdNotFoundException::new));

        if (courseThumbsupId.isPresent()) {
            courseThumbsupRepository.deleteById(courseThumbsupId.get().get(0).getId());
        }

    }

    // 내가 누른 해당 코스 좋아요(코스 한 개)
    @Override
    public List<CourseThumbsupView> readCourseThumbsupByMe(Long id) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Optional<List<CourseThumbsupEntity>> courseThumbsupEntityList =
                Optional.of(Optional.ofNullable(courseThumbsupRepository.findByAuthIdAndCourseId(authId, id))
                        .orElseThrow(DuplicateCourseThumbsup::new));


        return  courseThumbsupEntityList.get().stream().map(courseThumbsup ->
                new CourseThumbsupView(courseThumbsup)).collect(Collectors.toList());
    }

    // 코스의 좋아요 개수 조회
    @Override
    public CourseThumbsupCountView readCourseThumbsupCount(Long id) {
        Long courseThumbsupCnt = courseThumbsupRepository.countByCourseId(id);

        return CourseThumbsupCountView.builder()
                .courseThumbsCnt(courseThumbsupCnt)
                .build();
    }

    // 내가 누른 코스 좋아요 전체조회(마이페이지)
    @Override
    public List<CourseThumbsupView> readAllThumbsupByMe() {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long authId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        List<CourseThumbsupEntity> courseThumbsupEntityList = courseThumbsupRepository.findAllByAuthId(authId);
        List<CourseThumbsupView> courseThumbsupViews = new ArrayList<>();

        for (CourseThumbsupEntity courseThumbsupEntity : courseThumbsupEntityList) {
            courseThumbsupViews.add(CourseThumbsupView.builder()
                    .authId(courseThumbsupEntity.getAuthId())
                    .courseId(courseThumbsupEntity.getCourseId())
                    .build());
        }

        return courseThumbsupViews;
    }
}
