package com.college.courses;

import com.college.ROLE;
import com.college.enrollement.EnrollmentEntity;
import com.college.enrollement.EnrollmentRepository;
import com.college.enrollement.StudentCourse;
import com.college.enrollement.StudentCourseMapper;
import com.college.exception.AlreadyExistException;
import com.college.exception.CourseNotTakenException;
import com.college.exception.ResourceNotFoundException;
import com.college.users.UserEntity;
import com.college.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;

    private final UserRepository userRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final CourseDTOMapper courseDTOMapper;
    private final StudentCourseMapper mapper;
    public CourseService(CourseRepository repository, UserRepository userRepository, EnrollmentRepository enrollmentRepository, CourseDTOMapper courseDTOMapper, StudentCourseMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseDTOMapper = courseDTOMapper;
        this.mapper = mapper;
    }

    public List<String> getCoursesForTeacher(String email)
    {
        return repository.findByTeachers_EmailLike(email).stream().map(CourseEntity::getName).collect(Collectors.toList());
    }

    public List<String> getCoursesNotForTeacher(String email)
    {
        return repository.findByTeachers_EmailNotLike(email).stream().map(CourseEntity::getName).collect(Collectors.toList());
    }

    public List<CourseDTO> allCourses()
    {
        return repository.findAll().stream().map(courseDTOMapper).collect(Collectors.toList());
    }
    public CourseDTO getCourse(String name)
    {
        return repository.findById(name).map(courseDTOMapper).orElseThrow(()->new ResourceNotFoundException("Cannot find course for "+name));
    }
    public void addCourse(CourseEntity course)
    {
        if(repository.existsById(course.getName()))
            throw new AlreadyExistException("Course already exists");
        repository.save(course);
    }

    public void addTeacherToCourse(TeacherCourse teacherCourse) throws IllegalAccessException {
        Optional<CourseEntity> optionalCourseEntity = repository.findById(teacherCourse.course());
        UserEntity teacherEntity = userRepository.findById(teacherCourse.teacher()).orElseThrow(()->new ResourceNotFoundException("Teacher does not exist"));
        if(optionalCourseEntity.isPresent()) {
            CourseEntity courseEntity = optionalCourseEntity.get();
            if (teacherEntity.getRole().equals(ROLE.STUDENT)) {
                throw new IllegalAccessException("student cannot teach a course");
            }
            if (courseEntity.getTeachers() == null) {
                HashSet<UserEntity> teachers = new HashSet<>();
                teachers.add(teacherEntity);
                courseEntity.setTeachers(teachers);
                repository.save(courseEntity);
                return;
            }
            if (courseEntity.getTeachers().contains(teacherEntity))
                throw new AlreadyExistException("Teacher already teaching the course");
            else {
                courseEntity.getTeachers().add(teacherEntity);
                repository.save(courseEntity);
            }
        }
        else
        {
            String description = teacherCourse.description()== null? "":teacherCourse.description();
            CourseEntity course = new CourseEntity(teacherCourse.course(), description,teacherEntity);
            repository.save(course);
        }
    }

    public void removeTeacherFromCourse(TeacherCourse teacherCourse)
    {
        CourseEntity courseEntity = getFullCourse(teacherCourse.course());
        UserEntity teacherEntity = userRepository.findById(teacherCourse.teacher()).orElseThrow(()->new ResourceNotFoundException("Teacher does not exist"));
        if(courseEntity.getTeachers() ==null || !courseEntity.getTeachers().contains(teacherEntity))
        {
            throw new RuntimeException("Teacher is not teaching the course");
        }
        courseEntity.getTeachers().remove(teacherEntity);
        repository.save(courseEntity);
    }

    public CourseEntity getFullCourse(String name)
    {
        return repository.findById(name).orElseThrow(()->new ResourceNotFoundException("Teacher does not exist"));
    }

    public void updateGrade(GradingRequest request)
    {
        EnrollmentEntity enrollment = enrollmentRepository.findByCourse_NameLikeAndStudent_EmailLike(request.course(), request.studentEmail()).orElseThrow(()->new CourseNotTakenException("The student is not registered to this course"));
        enrollment.setGrade(request.grade());
        enrollmentRepository.save(enrollment);
    }

    public List<StudentCourse> getStudentCoursesOfTeacher(String teacher)
    {
        UserEntity teacherEntity = userRepository.findById(teacher).orElseThrow(()->new ResourceNotFoundException("Teacher does not exist"));
        return enrollmentRepository.findByTeacher_EmailLike(teacher).stream().map(mapper).collect(Collectors.toList());
    }

}
