package com.college.enrollement;

import java.util.Map;

public record CourseStudentTaken(
    String course,
    Map<String,String> teachers,
    boolean taken
) {
}
