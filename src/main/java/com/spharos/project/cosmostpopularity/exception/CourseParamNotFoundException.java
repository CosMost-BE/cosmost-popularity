package com.spharos.project.cosmostpopularity.exception;

import java.util.NoSuchElementException;

public class CourseParamNotFoundException extends NoSuchElementException {

    private static final String MESSAGE = "입력된 쿼리값이 올바르지 않습니다.";

    public CourseParamNotFoundException() {
        super(MESSAGE);
    }
}
