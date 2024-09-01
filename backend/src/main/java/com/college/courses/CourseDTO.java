package com.college.courses;

import java.util.List;

public record CourseDTO(
        String name, String description, List<String> teacher
        ){
}
