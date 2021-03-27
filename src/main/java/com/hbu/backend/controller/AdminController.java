package com.hbu.backend.controller;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.CourseDTO;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.Course;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/student")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable long studentId){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/instructor/student/{studentId}")
    public ResponseEntity<StudentDTO> getInstructorByStudent(@PathVariable long studentId){
        throw new UnsupportedOperationException();
    }


    // ---------- Instructor ----------
    @PostMapping("/instructor")
    public ResponseEntity<InstructorDTO> saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<Long> deleteInstructor(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable long instructorId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/instructor")
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/instructor/course/{courseId}")
    public ResponseEntity<CourseDTO> getInstructorByCourse(@PathVariable long courseId){
        throw new UnsupportedOperationException();
    }

    // ----------- course ------------
    @PostMapping("/course")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable long courseId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/course/instructor/{instructorId}")
    public ResponseEntity<InstructorDTO> getCourseByInstructor(@PathVariable long instructorId){
        throw new UnsupportedOperationException();
    }

    // ------------ admin --------------
    @PostMapping
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable long adminId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){
        throw new UnsupportedOperationException();
    }

}
