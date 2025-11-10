package com.school.schoolSystem.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
