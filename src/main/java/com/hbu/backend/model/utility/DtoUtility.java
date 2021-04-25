package com.hbu.backend.model.utility;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.UserDTO;
import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.User;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;

import java.util.List;

public class DtoUtility {
    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setClassSubject(course.getClassSubject());
        courseDTO.setCourseNumber(course.getCourseNumber());
//        courseDTO.setInstructorId(course.getInstructor().getId());
//        courseDTO.setStartTime(course.getStartTime());
//        courseDTO.setEndTime(course.getEndTime());
        return courseDTO;
    }

    public static Course toCourseEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
        course.setClassSubject(courseDTO.getClassSubject());
        course.setTitle(courseDTO.getTitle());
        course.setCourseNumber(courseDTO.getCourseNumber());
//        course.setStartTime(courseDTO.getStartTime());
//        course.setEndTime(courseDTO.getEndTime());
        return course;
    }

    public static AdminDTO toAdminDTO(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setId(admin.getId());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setRoleType(admin.getRoleType());

        return adminDTO;
    }

    public static Admin toAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());
        admin.setUsername(adminDTO.getUsername());
        admin.setRoleType(adminDTO.getRoleType());

        return admin;
    }

    public static StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(student.getId(), student.getStudentUniversityId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getUsername(), student.getCourses(), student.getRoleType());
    }

    public static Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentUniversityId(studentDTO.getStudentUniversityId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setUsername(studentDTO.getEmail());
        student.setCourses(studentDTO.getCourses());
        student.setRoleType(studentDTO.getRoleType());
        return student;
    }

    public static InstructorDTO toInstructorDTO(Instructor instructor) {
        return new InstructorDTO(instructor.getId(), instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getUsername(), instructor.getCourses(), instructor.getRoleType());
    }

    public static Instructor toInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setUsername(instructorDTO.getUsername());
        instructor.setCourses(instructorDTO.getCourses());
        instructor.setRoleType(instructorDTO.getRoleType());
        return instructor;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setRoleType(userDTO.getRoleType());
        return user;
    }

    /**
     * User conversion
     */
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = setBasicUserFields(user);
        RoleType roleType = userDTO.getRoleType();

        if (roleType == RoleType.ADMIN) {
            return DtoUtility.toUserDTO((Admin) user);
        }
        if (roleType == RoleType.INSTRUCTOR) {
            return DtoUtility.toUserDTO((Instructor) user);
        }
        if (roleType == RoleType.STUDENT) {
            return DtoUtility.toUserDTO((Student) user);
        }
        return userDTO;
    }

    public static UserDTO toUserDTO(Admin admin) {
        UserDTO userDTO = setBasicUserFields(admin);
        return userDTO;
    }

    public static UserDTO toUserDTO(Instructor instructor) {
        UserDTO userDTO = setBasicUserFields(instructor);
        userDTO.setCourses(instructor.getCourses());
        return userDTO;
    }

    public static UserDTO toUserDTO(Student student) {
        UserDTO userDTO = setBasicUserFields(student);
        userDTO.setCourses(student.getCourses());
        userDTO.setStudentUniversityId(student.getStudentUniversityId());
        return userDTO;
    }

    /**
     * sets common fields found in parent class User
     */
    public static UserDTO setBasicUserFields(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoleType(user.getRoleType());
        return userDTO;
    }
}
