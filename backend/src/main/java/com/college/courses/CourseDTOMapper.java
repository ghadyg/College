package com.college.courses;

import com.college.users.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CourseDTOMapper implements Function<CourseEntity,CourseDTO> {
    @Override
    public CourseDTO apply(CourseEntity course) {
        return new CourseDTO(
            course.getName(),
            course.getDescription(),
            course.getTeachers().stream().map(UserEntity::getName).collect(Collectors.toList())
        );
    }
}
