package com.college.courses;

import com.college.enrollement.StudentCourse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }
    @GetMapping("/courses/{teacher}")
    public List<String> getCoursesForTeacher(@PathVariable("teacher") String email)
    {
        return service.getCoursesForTeacher(email);
    }
    @GetMapping("/missingCourses/{teacher}")
    public List<String> getCoursesNotForTeacher(@PathVariable("teacher") String email)
    {
        return service.getCoursesNotForTeacher(email);
    }

    @GetMapping("/teacherCourses/{teacher}")
    public List<StudentCourse> getStudentCoursesOfTeacher(@PathVariable("teacher") String email)
    {
        return service.getStudentCoursesOfTeacher(email);
    }

    @GetMapping("/allCourses")
    public List<CourseDTO> getAllCourses()
    {
        return service.allCourses();
    }
    @GetMapping("/course/{name}")
    public CourseDTO getCourse(@PathVariable("name") String name)
    {
        return service.getCourse(name);
    }
    @PostMapping("/course")
    public void addCourse(@RequestBody CourseEntity course){
        service.addCourse(course);
    }

    @PutMapping("/course")
    public void addTeacherToCourse(@RequestBody TeacherCourse teacherCourse) throws IllegalAccessException {
        service.addTeacherToCourse(teacherCourse);
    }

    @DeleteMapping("/course")
    public void removeTeacherToCourse(@RequestBody TeacherCourse teacherCourse){
        service.removeTeacherFromCourse(teacherCourse);
    }

    @PutMapping("/grade")
    public void updateGrade(@RequestBody GradingRequest request){

        service.updateGrade(request);
    }

}
