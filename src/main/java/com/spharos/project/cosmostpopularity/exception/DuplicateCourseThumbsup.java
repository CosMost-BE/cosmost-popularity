package com.spharos.project.cosmostpopularity.exception;

public class DuplicateCourseThumbsup extends RuntimeException {

    private static final String MESSAGE = "좋아요가 중복이 되었습니다.";

    public DuplicateCourseThumbsup() {
        super(MESSAGE);
    }

}
