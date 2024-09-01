package com.college.courses;

import com.college.enrollement.EnrollmentEntity;
import com.college.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CourseEntity {
    @Column(
        name = "Name"
    )
    @Id
    private String name;

    @Column(
        name = "Description"
    )
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "TeacherCourses",
        joinColumns = @JoinColumn(name = "CourseEntity_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher")
    )
    private Set<UserEntity> teachers;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<EnrollmentEntity> enrollmentEntities;


    public CourseEntity() {
    }

    public CourseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public CourseEntity(String name, String description,UserEntity teacher) {
        this.name = name;
        this.description = description;
        this.teachers = Set.of(teacher);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(HashSet<UserEntity> teachers) {
        this.teachers = teachers;
    }
    @JsonIgnore
    public Set<EnrollmentEntity> getEnrollments() {
        return enrollmentEntities;
    }

    public void setEnrollments(HashSet<EnrollmentEntity> enrollmentEntities) {
        this.enrollmentEntities = enrollmentEntities;
    }


}
