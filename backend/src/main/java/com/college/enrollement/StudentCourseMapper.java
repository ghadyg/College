package com.college.enrollement;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentCourseMapper implements Function<EnrollmentEntity,StudentCourse> {
    @Override
    public StudentCourse apply(EnrollmentEntity enrollment) {
        return new StudentCourse(
            enrollment.getStudent().getEmail(),
            enrollment.getCourse().getName(),
            enrollment.getGrade()
        );
    }
}
