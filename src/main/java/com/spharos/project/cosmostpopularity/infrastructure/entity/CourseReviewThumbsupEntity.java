package com.spharos.project.cosmostpopularity.infrastructure.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "course_review_thumbsup")
public class CourseReviewThumbsupEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long authId;

    @NotNull
    private Long courseReviewId;

    @Builder
    public CourseReviewThumbsupEntity(Long authId, Long courseReviewId) {
        this.authId = authId;
        this.courseReviewId = courseReviewId;
    }

}
