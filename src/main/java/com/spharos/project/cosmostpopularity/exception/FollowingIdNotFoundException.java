package com.spharos.project.cosmostpopularity.exception;

public class FollowingIdNotFoundException extends RuntimeException {
    private static final String MESSAGE = "팔로잉ID가 존재하지 않습니다";

    public  FollowingIdNotFoundException() {
        super(MESSAGE);
    }

}
