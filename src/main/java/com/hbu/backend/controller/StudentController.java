package com.hbu.backend.controller;

import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.request.StudentRequestAccessAcademic;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final static String TAG_ = "StudentController";

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        Student student = DtoUtility.toStudent(studentDTO);
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<StudentDTO>(DtoUtility.toStudentDTO(newStudent), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable long studentId) {
        Student student = studentService.findStudent(studentId);

        if(student == null){
            return new ResponseEntity("Student "  + studentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<StudentDTO>(DtoUtility.toStudentDTO(student), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<Student> students = studentService.findAllStudents();

        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(Student student : students){
            studentDTOs.add(DtoUtility.toStudentDTO(student));
        }

        return new ResponseEntity<List<StudentDTO>>(studentDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id){
        Student student = studentService.updateStudent(DtoUtility.toStudent(studentDTO), id);

        if(student == null){
            return new ResponseEntity("Student " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<StudentDTO>(DtoUtility.toStudentDTO(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);

        if(student == null){
            return new ResponseEntity("Student " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        studentService.deleteStudent(student);
        return new ResponseEntity("Deleted Student " + id, HttpStatus.OK);
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
