package com.college.exception;

public class CourseNotTakenException extends RuntimeException{
    public CourseNotTakenException(String message) {
        super(message);
    }

    public CourseNotTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    protected CourseNotTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
