package com.spharos.project.cosmostpopularity.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "course_thumbsup")
public class CourseThumbsupEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authId;

    private Long courseId;

    @Builder
    public CourseThumbsupEntity(Long id, Long authId, Long courseId) {
        this.id = id;
        this.authId = authId;
        this.courseId = courseId;
    }
}
