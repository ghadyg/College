package com.college.enrollement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Integer> {

    List<EnrollmentEntity> findByStudent_EmailLike(String email);

    List<EnrollmentEntity> findByStudent_EmailNotLike(String email);

    Optional<EnrollmentEntity> findByCourse_NameLikeAndStudent_EmailLike(String name, String email);

    List<EnrollmentEntity> findByTeacher_EmailLike(String email);
}
