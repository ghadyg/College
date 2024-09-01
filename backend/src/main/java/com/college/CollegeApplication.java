package com.college;

import com.college.courses.CourseEntity;
import com.college.courses.CourseRepository;
import com.college.enrollement.EnrollmentEntity;
import com.college.enrollement.EnrollmentRepository;
import com.college.users.UserEntity;
import com.college.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										EnrollmentRepository enrollmentRepository,
										CourseRepository courseRepository)  {
		List<CourseEntity> byTeachersNameLike = courseRepository.findByTeachers_EmailLike("CharlesRodriguez@gmail.com");
		System.out.println(byTeachersNameLike);


		return args -> {

		};
		};

}
