package com.spharos.project.cosmostpopularity.exception;

import java.util.NoSuchElementException;

public class FollowIdNotFoundException extends NoSuchElementException {
    private static final String MESSAGE = "팔로우를 찾을 수 없습니다.";

    public  FollowIdNotFoundException() {
        super(MESSAGE);
    }

}
