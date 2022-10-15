package com.spharos.project.cosmostpopularity.exception;

import java.util.NoSuchElementException;

public class CourseReviewThumbsupNotFoundException extends NoSuchElementException {

    private static final String MESSAGE = "코스리뷰 좋아요를 찾을 수 없습니다.";

    public  CourseReviewThumbsupNotFoundException() {
        super(MESSAGE);
    }

}
