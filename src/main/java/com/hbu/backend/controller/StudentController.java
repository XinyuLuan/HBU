package com.hbu.backend.controller;

import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.dto.StudentTranscriptDTO;
import com.hbu.backend.model.entity.Course;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.request.StudentRequestAccessAcademic;
import com.hbu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final static String TAG_ = "StudentController";

    @Autowired
    StudentService studentService;

    // convert entity to DTO
    private StudentDTO convertStudentToStudentDTO(Student student) {
        return new StudentDTO(student.getId(), student.getStudentId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getUsername(), student.getCourses());
    }

    // convert DTO to entity
    private Student convertStudentDtoToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentId(studentDTO.getStudentId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setUsername(studentDTO.getEmail());
        student.setCourses(studentDTO.getCourses());
        return student;
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        Student student = convertStudentDtoToStudent(studentDTO);
        StudentDTO convertedStudent;
        try {
            convertedStudent = convertStudentToStudentDTO(studentService.addStudent(student));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student could not be saved", exception);
        }
        return convertedStudent;
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        Student student;
        try {
            student = studentService.findStudent(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with id: " + id + " not found", exception);
        }
        return convertStudentToStudentDTO(student);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentService.findAllStudents();

        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(Student student : students) {
            studentDTOs.add(convertStudentToStudentDTO(student));
        }
        return studentDTOs;
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        Student student;
        try {
            student = studentService.updateStudent(convertStudentDtoToStudent(studentDTO), id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student object is null", exception);
        }
        return convertStudentToStudentDTO(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);

        if (student == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "student " + id + " does not exist");
        }

        studentService.deleteStudent(student);
        return new ResponseEntity("Deleted " + id, HttpStatus.OK);
    }

    // add course
    public boolean addCourse(long courseID){
        // verify course id
        // if course id exists in database,
        // then check the requirements for adding a class (make sure condition and date range is not violated)
        return false;
    }

    // drop class
    public boolean dropCourse(long courseID){
        // similar logic compared to addCourse method
        return false;
    }

    // update course (has the ability to add or drop course)
    public boolean updateCourse(long courseID, int actionRequest){
        // verify course id
        // check if the action request is permitted
        return false;
    }

    // view schedule of courses
    public List<Course> getScheduleOfCourses(long studentID){
        // if student id is valid, then return their schedule
        return null;
    }

    // view course search page
    public List<Course> getAvailableCourses(){
        // use course controller to access all available courses for the semester
        return null;
    }

    // view class registration page (does this use a signup controller?)

    // update student profile
    public boolean updateProfile(long studentID){

        return false;
    }

//    @GetMapping("/academic_page")
//    public String goAcademicsPage(StudentRequestAccessAcademic request){
//        //process the request
//        return "academicpage";
//    }

    @GetMapping("/unoffical_transcript")
    public String goUnofficalTrascript(StudentRequestAccessAcademic request){
        //process the request
//        StudentTranscriptDTO s = studentService.getUnofficalTranscript(...., ....);
        return null;
    }

    // login to dashboard

    // view course grades

    // order official transcript

    // complete survey questionnaire

}
