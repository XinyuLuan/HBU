package com.hbu.backend.controller;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.service.AdminService;
import com.hbu.backend.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    String _Tag = "AdminController ";
    String _ErrorAdmin = "_ErrorAdmin ";

    @Autowired
    AdminService adminService;

    /**
     * for admins
     */
    @PostMapping
    public ResponseEntity<AdminDTO> addAdmin(@RequestBody AdminDTO adminDTO){
        Admin admin = DtoUtility.toAdmin(adminDTO);
        Admin newAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<AdminDTO>(DtoUtility.toAdminDTO(newAdmin), HttpStatus.OK);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable long adminId) {
        Admin admin = adminService.findAdmin(adminId);

        if(admin == null){
            return new ResponseEntity(_ErrorAdmin + adminId + "NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AdminDTO>(DtoUtility.toAdminDTO(admin), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){
        List<Admin> admins = adminService.findAllAdmin();

        List<AdminDTO> adminDTOs = new ArrayList<>();
        for(Admin admin : admins){
            adminDTOs.add(DtoUtility.toAdminDTO(admin));
        }

        return new ResponseEntity<List<AdminDTO>>(adminDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO, @PathVariable Long id){
        Admin admin = adminService.updateAdmin(DtoUtility.toAdmin(adminDTO), id);

        if(admin == null){
            return new ResponseEntity(_ErrorAdmin + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AdminDTO>(DtoUtility.toAdminDTO(admin), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        Admin admin = adminService.findAdmin(id);

        if(admin == null){
            return new ResponseEntity(_ErrorAdmin + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        adminService.deleteAdmin(admin);
        return new ResponseEntity("Deleted Admin " + id, HttpStatus.OK);
    }

    /**
     * for students
     */
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
}
