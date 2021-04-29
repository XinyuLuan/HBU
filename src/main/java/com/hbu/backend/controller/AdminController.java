package com.hbu.backend.controller;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.dto.course.CourseModuleDTO;
import com.hbu.backend.model.dto.course.GradeDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import com.hbu.backend.model.entity.course.Grade;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.AdminService;


import com.hbu.backend.service.InstructorService;
import com.hbu.backend.service.StudentService;
import com.hbu.backend.service.course.CourseModuleService;
import com.hbu.backend.service.course.CourseService;
import com.hbu.backend.service.course.GradeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    String _Tag = "AdminController ";
    String _ErrorAdmin = "_ErrorAdmin ";
    String _COURSE_ERROR = "Course ";
    String _INSTRUCTOR_ERROR = "Instructor ";
    String _STUDENT_ERROR = "Student ";

    private final static String TAG_ = "AdminController";

    @Autowired
    AdminService adminService;
    @Autowired
    CourseService courseService;
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseModuleService courseModuleService;
    @Autowired
    StudentService studentService;
    @Autowired
    GradeService gradeService;

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
            return new ResponseEntity(_ErrorAdmin + adminId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity(_ErrorAdmin + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AdminDTO>(DtoUtility.toAdminDTO(admin), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        Admin admin = adminService.findAdmin(id);

        if(admin == null){
            return new ResponseEntity(_ErrorAdmin + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        adminService.deleteAdmin(admin);
        return new ResponseEntity("Deleted Admin " + id, HttpStatus.OK);
    }

    /**
     * for students
     */
    @PostMapping("/student")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        Student student = com.hbu.backend.model.utility.DtoUtility.toStudent(studentDTO);
//        student.setCourseModules(new ArrayList<>());

        List<CourseModule> courseModules = new ArrayList<>();
        if(studentDTO.getCourseModuleIds() != null){
            for(Long courseModuleId : studentDTO.getCourseModuleIds()){
                CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);

                if(courseModule == null){
                    continue;
                }

//                courseModule.addStudent(student);
//                courseModuleService.saveCourseModule(courseModule);
//                student.addCourseModule(courseModule);
                courseModules.add(courseModule);
            }
        }

        student.setCourseModules(courseModules);

        Student newStudent = studentService.addStudent(student);
        for(CourseModule courseModule : courseModules){
            courseModule.addStudent(newStudent);
            courseModuleService.saveCourseModule(courseModule);
        }


        return new ResponseEntity<StudentDTO>(com.hbu.backend.model.utility.DtoUtility.toStudentDTO(newStudent), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if(student == null){
            return new ResponseEntity("Student " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }


        List<CourseModule> courseModules = student.getCourseModules();
        for(CourseModule courseModule : courseModules){
            courseModule.deleteStudent(student);
            courseModule.deleteGrade(student);
            Grade grade = courseModule.findGrade(student.getId());
            if(grade != null){
                courseModule.deleteGrade(student);
//                gradeService.deleteGrade(grade.getId());
            }
            courseModuleService.saveCourseModule(courseModule);
        }

        student.getCourseModules().clear();
//        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModuleService.saveCourseModule(courseModule)), HttpStatus.OK);
//        Student student = studentService.findStudent(id);
//
//        if(student == null){
//            return new ResponseEntity("Student " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
//        }
//
        studentService.deleteStudent(student);
        return new ResponseEntity("Deleted Student " + id, HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id){
        Student student = studentService.updateStudent(com.hbu.backend.model.utility.DtoUtility.toStudent(studentDTO), id);

        if(student == null){
            return new ResponseEntity("Student " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        student.setCourseModules(new ArrayList<>());
        if(studentDTO.getCourseModuleIds() != null){
            for(Long courseModuleId : studentDTO.getCourseModuleIds()){
                CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
                if(courseModule == null){
                    continue;
                }
                student.addCourseModule(courseModule);
            }
        }

        return new ResponseEntity<StudentDTO>(com.hbu.backend.model.utility.DtoUtility.toStudentDTO(student), HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<Student> students = studentService.findAllStudents();

        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(Student student : students){
            studentDTOs.add(com.hbu.backend.model.utility.DtoUtility.toStudentDTO(student));
        }

        return new ResponseEntity<List<StudentDTO>>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable long studentId){
        Student student = studentService.findStudent(studentId);

        if(student == null){
            return new ResponseEntity("Student "  + studentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<StudentDTO>(com.hbu.backend.model.utility.DtoUtility.toStudentDTO(student), HttpStatus.OK);
    }

    @GetMapping("/instructor/student/{studentId}")
    public ResponseEntity<StudentDTO> getInstructorByStudent(@PathVariable long studentId){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/courses/student/{studentId}")
    public ResponseEntity<List<CourseDTO>> getCourseByStudent(@PathVariable long studentId){
        Student student = studentService.findStudent(studentId);

        if(student == null){
            return new ResponseEntity(_STUDENT_ERROR  + studentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        List<CourseModule> courseModules = student.getCourseModules();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for(CourseModule courseModule : courseModules){
            courseDTOs.add(DtoUtility.toCourseDTO(courseModule.getCourse()));
        }

        return new ResponseEntity<List<CourseDTO>>(courseDTOs, HttpStatus.OK);
    }

    @GetMapping("/courseModule/student/{studentId}")
    public ResponseEntity<List<CourseModuleDTO>> getcoursesModuleByStudent(@PathVariable long studentId){
        Student student = studentService.findStudent(studentId);

        if(student == null){
            return new ResponseEntity(_STUDENT_ERROR  + studentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        List<CourseModule> courseModules = student.getCourseModules();
        List<CourseModuleDTO> courseModuleDTOs = new ArrayList<>();
        for(CourseModule courseModule : courseModules){
            courseModuleDTOs.add(DtoUtility.toCourseModuleDTO(courseModule));
        }

        return new ResponseEntity<List<CourseModuleDTO>>(courseModuleDTOs, HttpStatus.OK);
    }

    @GetMapping("/grade/student/{studentId}")
    public ResponseEntity<List<GradeDTO>> getGradeModuleByStudent(@PathVariable long studentId){
        Student student = studentService.findStudent(studentId);

        if(student == null){
            return new ResponseEntity(_STUDENT_ERROR  + studentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        List<Grade> grades = gradeService.findGradesByStudentId(studentId);
        List<GradeDTO> gradeDTOs = new ArrayList<>();
        for(Grade grade : grades){
            gradeDTOs.add(DtoUtility.toGradeDTO(grade));
        }

        return new ResponseEntity<List<GradeDTO>>(gradeDTOs, HttpStatus.OK);
    }


    // ---------- Instructor ----------
    @PostMapping("/instructor/add")
    public ResponseEntity<InstructorDTO> saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = com.hbu.backend.model.utility.DtoUtility.toInstructor(instructorDTO);
        Instructor newInstructor = instructorService.addInstructor(instructor);
        return new ResponseEntity<InstructorDTO>(com.hbu.backend.model.utility.DtoUtility.toInstructorDTO(newInstructor), HttpStatus.OK);
    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<InstructorDTO> deleteInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findInstructor(id);

        if(instructor == null){
            return new ResponseEntity("Instructor " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        instructorService.deleteInstructor(instructor);
        return new ResponseEntity("Deleted Instructor " + id, HttpStatus.OK);
    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long id){
        Instructor instructor = instructorService.findInstructor(id);

        if(instructor == null){
            return new ResponseEntity("Instructor " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

//        instructor.setCourses(instructorDTO.getCourses());
        instructor.getCourseModules().clear();
        List<CourseModule> courseModules = new ArrayList<>();
        for(Long courseModuleId : instructorDTO.getCourseModules()){
            CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
            if(courseModule == null){
                return new ResponseEntity("Course Module " + courseModuleId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
            }

        }
        instructor.setCourseModules(courseModules);
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setUsername(instructorDTO.getUsername());
        instructor = instructorService.updateInstructor(instructor, id);
        return new ResponseEntity<InstructorDTO>(com.hbu.backend.model.utility.DtoUtility.toInstructorDTO(instructor), HttpStatus.OK);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable long instructorId) {
        Instructor instructor = instructorService.findInstructor(instructorId);

        if(instructor == null){
            return new ResponseEntity("Instructor " + instructorId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<InstructorDTO>(com.hbu.backend.model.utility.DtoUtility.toInstructorDTO(instructor), HttpStatus.OK);

    }

    @GetMapping("/instructor")
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(){
        List<Instructor> instructors = instructorService.findAllInstructors();

        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for(Instructor instructor : instructors){
            instructorDTOs.add(com.hbu.backend.model.utility.DtoUtility.toInstructorDTO(instructor));
        }

        return new ResponseEntity<List<InstructorDTO>>(instructorDTOs, HttpStatus.OK);
    }

    /**
     * Admin controller for finding course modules by a course
     * @param instructorId An id of an instructor
     * @return all course module teached or is teaching by a instructor with instructorId
     */
    @GetMapping("/getCourseModuleByInstructor/{instructorId}")
    public ResponseEntity<List<CourseModuleDTO>> getCourseModuleByInstructor(@PathVariable long instructorId){
        Instructor instructor = instructorService.findInstructor(instructorId);

        if(instructor == null){
            return new ResponseEntity("Instructor " + instructorId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

//        Course course = courseService.findCourse(courseId);
        List<CourseModule> courseModules = courseModuleService.findCourseModulesByInstructorId(instructorId);

        if(courseModules == null){
            return new ResponseEntity(_INSTRUCTOR_ERROR + " Id " + instructorId + " HAS NO COURSE RELATED", HttpStatus.BAD_REQUEST);
        }

        List<CourseModuleDTO> courseModuleDTOs = new ArrayList<>();
        for(CourseModule courseModule : courseModules){
            courseModuleDTOs.add(DtoUtility.toCourseModuleDTO(courseModule));
        }

        return new ResponseEntity<List<CourseModuleDTO>>(courseModuleDTOs, HttpStatus.OK);
    }

    /**
     * Admin controller for Course
     */

    @PostMapping("/course/add")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
        Course course = DtoUtility.toCourseEntity(courseDTO);
        Course newCourse = courseService.saveCourse(course);
        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(newCourse), HttpStatus.OK);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        Course course = courseService.findCourse(id);

        if(course == null){
            return new ResponseEntity(_COURSE_ERROR + "Id " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        courseService.deleteCourse(course);
        return ResponseEntity.ok("Deleted course " + id);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        Course course = courseService.updateCourse(DtoUtility.toCourseEntity(courseDTO), id);

        if(course == null){
            return new ResponseEntity(_COURSE_ERROR + "Id " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(course), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable long courseId) {
        Course course = courseService.findCourse(courseId);

        if(course == null){
            return new ResponseEntity(_COURSE_ERROR + "Id " + courseId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(course), HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<Course> courses = courseService.findAllCourse();

        List<CourseDTO> courseDTOs = new ArrayList<>();
        for(Course course : courses){
            courseDTOs.add(DtoUtility.toCourseDTO(course));
        }

        return new ResponseEntity<List<CourseDTO>>(courseDTOs, HttpStatus.OK);
    }



    @GetMapping("/instructorByCourse/{courseId}")
    public ResponseEntity<List<InstructorDTO>> getInstructorsByCourse(@PathVariable long courseId){
        Course course = courseService.findCourse(courseId);

        if(course == null){
            return new ResponseEntity(_COURSE_ERROR + "Id " + courseId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        List<CourseModule> courseModules = course.getCourseModules();
        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for(CourseModule courseModule : courseModules){
            instructorDTOs.add(com.hbu.backend.model.utility.DtoUtility.toInstructorDTO(courseModule.getInstructor()));
        }

        return new ResponseEntity<List<InstructorDTO>>(instructorDTOs, HttpStatus.OK);
    }

    @GetMapping("/courseModuleByCourse/{courseId}")
    public ResponseEntity<List<CourseModuleDTO>> getCourseModulesByCourse(@PathVariable long courseId){
        Course course = courseService.findCourse(courseId);

        if(course == null){
            return new ResponseEntity(_COURSE_ERROR + "Id " + courseId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        List<CourseModule> courseModules = course.getCourseModules();
        List<CourseModuleDTO> courseModuleDTOs = new ArrayList<>();
        for(CourseModule courseModule : courseModules){
            courseModuleDTOs.add(DtoUtility.toCourseModuleDTO(courseModule));
        }

        return new ResponseEntity<List<CourseModuleDTO>>(courseModuleDTOs, HttpStatus.OK);
    }
}
