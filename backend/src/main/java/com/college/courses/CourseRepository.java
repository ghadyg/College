package com.college.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,String> {


    List<CourseEntity> findByEnrollmentEntities_Student_EmailNotLike(String email);

    List<CourseEntity> findByTeachers_EmailLike(String email);

    List<CourseEntity> findByTeachers_EmailNotLike(String email);

    @Query("SELECT c FROM CourseEntity c WHERE NOT EXISTS (SELECT e FROM EnrollmentEntity e WHERE e.course = c AND e.student.email = :studentEmail)")
    List<CourseEntity> findCoursesNotEnrolledByStudent(@Param("studentEmail") String studentEmail);
}
