package com.college.enrollement;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TeacherCourseGradeMapper implements Function<EnrollmentEntity, TeacherCourseGrade> {
    @Override
    public TeacherCourseGrade apply(EnrollmentEntity enrollment) {
        return new TeacherCourseGrade(
                enrollment.getTeacher().getName(),
                enrollment.getCourse().getName(),
                enrollment.getGrade()
        );
    }
}
