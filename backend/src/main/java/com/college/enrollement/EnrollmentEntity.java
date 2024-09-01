package com.college.enrollement;

import com.college.courses.CourseEntity;
import com.college.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class EnrollmentEntity {

    @Id
    @Column(
        name = "id"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Course",nullable = false)
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "Teacher",nullable = false)
    private UserEntity teacher;

    @ManyToOne
    @JoinColumn(name = "Student",nullable = false)
    private UserEntity student;


    @Column(
        name = "Grade"
    )
    private Double grade;


    public EnrollmentEntity() {
    }

    public EnrollmentEntity(CourseEntity course, UserEntity teacher, UserEntity student, Double grade) {
        this.course = course;
        this.teacher = teacher;
        this.student = student;
        this.grade = grade;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UserEntity teacher) {
        this.teacher = teacher;
    }

    public UserEntity getStudent() {
        return student;
    }

    public void setStudent(UserEntity student) {
        this.student = student;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
