package com.college.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handle(
            Exception e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(
            ResourceNotFoundException e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionBody> handle(
            AlreadyExistException e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalAccessException .class)
    public ResponseEntity<ExceptionBody> handle(
            IllegalAccessException  e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CourseNotTakenException .class)
    public ResponseEntity<ExceptionBody> handle(
            CourseNotTakenException  e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(
            UsernameNotFoundException  e,
            HttpServletRequest request){
        ExceptionBody apiError = new ExceptionBody(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }
}
