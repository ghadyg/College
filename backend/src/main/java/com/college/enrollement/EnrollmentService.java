package com.college.enrollement;

import com.college.courses.CourseEntity;
import com.college.courses.CourseRepository;
import com.college.exception.CourseNotTakenException;
import com.college.exception.ResourceNotFoundException;
import com.college.users.UserEntity;
import com.college.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository  repository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TeacherCourseGradeMapper mapper;

    public EnrollmentService(EnrollmentRepository repository, UserRepository userRepository, CourseRepository courseRepository, TeacherCourseGradeMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    public List<TeacherCourseGrade> getStudentCourse(String studentEmail)
    {
        UserEntity user = userRepository.findById(studentEmail).orElseThrow(()->new ResourceNotFoundException("Student does not exist"));
        return repository.findByStudent_EmailLike(studentEmail).stream().map(mapper).collect(Collectors.toList());

    }
    public List<String> getNameCourses(String studentEmail)
    {
        return courseRepository.findCoursesNotEnrolledByStudent(studentEmail).stream().map(CourseEntity::getName).collect(Collectors.toList());
    }

    public void addEnrollment(RegisterRequest request)
    {
        CourseEntity courseEntity = courseRepository.findById(request.course()).orElseThrow(()->new ResourceNotFoundException("Course does not exist"));
        UserEntity teacher = userRepository.findById(request.teacher()).orElseThrow(()->new ResourceNotFoundException("Teacher does not exist"));
        UserEntity student = userRepository.findById(request.student()).orElseThrow(()->new ResourceNotFoundException("Student does not exist"));

        if(courseEntity.getTeachers() ==null || !courseEntity.getTeachers().contains(teacher))
            throw new ResourceNotFoundException("Teacher does not teach this course");

        if(courseEntity.getEnrollments()!=null &&
                courseEntity.getEnrollments().stream().anyMatch(enrollment -> enrollment.getStudent().getEmail().equals(request.student())))
            throw new CourseNotTakenException("The student is not registered to this course");

        EnrollmentEntity enrollment = new EnrollmentEntity(courseEntity,teacher,student,0d);
        repository.save(enrollment);

    }


    public void removeEnrollment(EnrollmentRemove enrollmentRemove)
    {
        EnrollmentEntity enrollment = repository.findByCourse_NameLikeAndStudent_EmailLike(enrollmentRemove.course(), enrollmentRemove.student())
                .orElseThrow(()->new CourseNotTakenException("The student is not registered to this course"));
        repository.delete(enrollment);
    }

    public List<CourseStudentTaken> isCourseTakenByStudent(String email)
    {
        List<CourseEntity> allCourses = courseRepository.findAll();
        List<EnrollmentEntity> enrolled = repository.findByStudent_EmailLike(email);
        List<CourseStudentTaken> list = new ArrayList<>();
        for (CourseEntity c: allCourses) {
            Optional<EnrollmentEntity> courseGrade = enrolled.stream().filter(enrollment -> enrollment.getCourse().getName().equals(c.getName())).findFirst();
            if(courseGrade.isPresent())
                list.add(new CourseStudentTaken(c.getName(),
                        Map.of(courseGrade.get().getTeacher().getEmail(),courseGrade.get().getTeacher().getName()),
                        true));
            else
                list.add(new CourseStudentTaken(c.getName(),
                        c.getTeachers().stream().collect(Collectors.toMap(UserEntity::getEmail,UserEntity::getName)),
                        false));

        }
        return list;

    }

}
