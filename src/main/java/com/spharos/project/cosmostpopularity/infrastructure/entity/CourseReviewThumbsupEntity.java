package com.spharos.project.cosmostpopularity.infrastructure.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "course_review_thumbsup")
public class CourseReviewThumbsupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long authId;

    @NotNull
    private Long courseId;

}
