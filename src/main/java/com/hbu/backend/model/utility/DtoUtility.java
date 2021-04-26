package com.hbu.backend.model.utility;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.CourseModule;

import java.util.ArrayList;
import java.util.List;

public class DtoUtility {
    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getId());
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
        course.setId(courseDTO.getCourseId());
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
        adminDTO.setAdminId(admin.getAdminId());
        return adminDTO;
    }

    public static  Admin toAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        return admin;
    }

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getStudentId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getUsername());

        List<Long> courseModuleIds = new ArrayList<>();
        if(student.getCourseModules() != null){
            for(CourseModule courseModule : student.getCourseModules()){
                courseModuleIds.add(courseModule.getId());
            }
            studentDTO.setCourseModuleIds(courseModuleIds);
        }

        return studentDTO;
    }

    public static Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentId(studentDTO.getStudentId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setUsername(studentDTO.getEmail());
//        student.setCourseModules(studentDTO.getCourseModuleIds());
        return student;
    }

    public static InstructorDTO toInstructorDTO(Instructor instructor) {
        return new InstructorDTO(instructor.getId(), instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getUsername(), instructor.getCourseModules());
    }

    public static Instructor toInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setUsername(instructorDTO.getUsername());
//        instructor.setCourses(instructorDTO.getCourses());
        instructor.setCourseModules(instructorDTO.getCourseModules());
        return instructor;
    }
}
