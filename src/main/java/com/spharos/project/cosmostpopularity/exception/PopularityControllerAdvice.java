package com.spharos.project.cosmostpopularity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PopularityControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String processValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        FieldError fieldError = bindingResult.getFieldError();
        builder.append(fieldError.getDefaultMessage());

        return builder.toString();
    }

    @ExceptionHandler(CourseReviewThumbsupNotFoundException.class)
    public ResponseEntity<String> CourseReviewThumbsupNotFoundException(CourseReviewThumbsupNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(CourseIdNotFoundException.class)
    public ResponseEntity<String> CourseIdNotFoundException(CourseIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
