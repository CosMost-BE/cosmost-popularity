package com.spharos.project.cosmostpopularity.exception;

import java.util.NoSuchElementException;

public class CourseIdNotFoundException extends NoSuchElementException {
    private static final String MESSAGE = "코스 좋아요를 찾을 수 없습니다.";

    public  CourseIdNotFoundException() {
        super(MESSAGE);
    }

}
