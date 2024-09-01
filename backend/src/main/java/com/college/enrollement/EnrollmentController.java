package com.college.enrollement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping("courses/{email}")
    public List<TeacherCourseGrade> getStudentCourse(@PathVariable("email") String studentEmail)
    {
            return service.getStudentCourse(studentEmail);
    }

    @GetMapping("courseName/{email}")
    public List<String> getNameCourses(@PathVariable("email") String studentEmail){
        return service.getNameCourses(studentEmail);
    }

    @GetMapping("studentCourses/{email}")
    public List<CourseStudentTaken> getstudentCourses(@PathVariable("email") String studentEmail){
        return service.isCourseTakenByStudent(studentEmail);
    }

    @PostMapping("/register")
    public void addEnrollment(@RequestBody RegisterRequest request)
    {
        service.addEnrollment(request);
    }

    @DeleteMapping("/enrollment")
    public void removeEnrollment(@RequestBody EnrollmentRemove enrollmentRemove)
    {
        service.removeEnrollment(enrollmentRemove);
    }
}
