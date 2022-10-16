package com.spharos.project.cosmostpopularity.exception;

public class AuthIdNotFoundException extends RuntimeException {

    private static final String MESSAGE = "사용자ID가 존재하지 않습니다";

    public  AuthIdNotFoundException() {
        super(MESSAGE);
    }

}
