package com.hbu.backend.controller.course;

import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.dto.course.ChapterDTO;
import com.hbu.backend.model.dto.course.CourseModuleDTO;
import com.hbu.backend.model.dto.course.GradeDTO;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.Chapter;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import com.hbu.backend.model.entity.course.Grade;
import com.hbu.backend.service.InstructorService;
import com.hbu.backend.service.StudentService;
import com.hbu.backend.service.course.ChapterService;
import com.hbu.backend.service.course.CourseModuleService;
import com.hbu.backend.service.course.CourseService;
import com.hbu.backend.service.course.GradeService;
import com.hbu.backend.utility.DtoUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.hibernate.graph.Graph;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/courseModule")
@Slf4j
public class CourseModuleController {

    @Autowired
    CourseModuleService courseModuleService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    StudentService studentService;
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseService courseService;
    @Autowired
    GradeService gradeService;

    /**
     * Create a course module, when we set up a course module there is no student join in , not chapter set up and no grade corresponding to student
     *
     * @param courseModuleDTO
     * @return updated courseModule
     */
    @PostMapping("/add")
    public ResponseEntity<CourseModuleDTO> saveCourseModule(@RequestBody CourseModuleDTO courseModuleDTO) {
        CourseModule courseModule = new CourseModule();
        courseModule.setInstructor(instructorService.findInstructor(courseModuleDTO.getInstructorId()));
        courseModule.setCourse(courseService.findCourse(courseModuleDTO.getCourseId()));
        Course course = courseService.findCourse(courseModule.getCourse().getId());

        courseModule.setChapters(new ArrayList<>());
        courseModule.setStudents(new HashSet<>());
        courseModule.setGrades(new HashSet<>());

        course.addCourseModule(courseModule);

        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModuleService.saveCourseModule(courseModule)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModuleDTO> getCourseModule(@PathVariable Long id) {
        CourseModule courseModule = courseModuleService.findCourseModule(id);

        if (courseModule == null) {
            return new ResponseEntity("CourseModel" + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModule), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CourseModuleDTO>> getAllCourseModule() {
        List<CourseModule> courseModules = courseModuleService.findAllCourseModule();

        List<CourseModuleDTO> courseModuleDTOs = new ArrayList<>();
        if (courseModules != null) {
            for (CourseModule courseModule : courseModules) {
                courseModuleDTOs.add(DtoUtility.toCourseModuleDTO(courseModule));
            }
        }

        return new ResponseEntity<List<CourseModuleDTO>>(courseModuleDTOs, HttpStatus.OK);
    }

    /**
     * Update course or instructor in a course module
     *
     * @param courseModuleDTO
     * @param id
     * @return
     */
    @PutMapping("/updateCouseModule/{id}")
    public ResponseEntity<CourseModuleDTO> updateCourseModule(@RequestBody CourseModuleDTO courseModuleDTO, @PathVariable Long id) {
        CourseModule courseModule = courseModuleService.findCourseModule(id);
        if (courseModule == null) {
            return new ResponseEntity("CourseModel " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Course course = courseService.findCourse(courseModuleDTO.getCourseId());
        if (course == null) {
            return new ResponseEntity("Course " + courseModuleDTO.getCourseId() + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        courseModule.setCourse(course);

        Instructor instructor = instructorService.findInstructor(courseModuleDTO.getInstructorId());
        if (instructor == null) {
            return new ResponseEntity("Instructor " + courseModuleDTO.getInstructorId() + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        courseModule.setInstructor(instructor);

        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModuleService.saveCourseModule(courseModule)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourseModule(@PathVariable Long id) {
        CourseModule courseModule = courseModuleService.findCourseModule(id);

        if (courseModule == null) {
            return new ResponseEntity("CourseModel " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Course course = courseModule.getCourse();
        course.deleteCourseModule(courseModule);

        courseService.saveCourse(course);

        // Clear all of the foreign key in course module
        courseModule.setInstructor(new Instructor());
        courseModule.setCourse(new Course());
        courseModule.setStudents(new HashSet<>());
        courseModule.setChapters(new ArrayList<>());
        courseModule.setGrades(new HashSet<>());
        courseModuleService.saveCourseModule(courseModule);

        courseModuleService.deleteCourseModule(id);
        return new ResponseEntity("Course Model " + id + " DELETE SUCCESSFUL", HttpStatus.OK);
    }

    @PostMapping("/createChapterInCourseModule/{courseModuleId}")
    public ResponseEntity<CourseModuleDTO> createChapterInCourseModule(@RequestBody ChapterDTO chapterDTO, @PathVariable Long courseModuleId) {
        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);

        if (courseModule == null) {
            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Chapter chapter = DtoUtility.toChapter(chapterDTO);
        chapterService.saveChapter(chapter);
        courseModule.addChapter(chapter);
        courseModule = courseModuleService.saveCourseModule(courseModule);
        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModule), HttpStatus.OK);
    }

    //    @PutMapping("/updateChapterInCourseModule/{courseModuleId}/{chapterId}")
//    public ResponseEntity<CourseModuleDTO> updateChapterInCourseModule(@RequestBody ChapterDTO chapterDTO,
//                                                                       @PathVariable Long courseModuleId,
//                                                                       @PathVariable Long chapterId){
//        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
//        if(courseModule == null){
//            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
//        }
//
////        Chapter chapter = DtoUtility.toChapter(chapterDTO);
//        Chapter chapter = chapterService.findChapter(chapterId);
//        if(chapter == null){
//            return new ResponseEntity("Chapter " + chapterId + " NOT EXIST", HttpStatus.BAD_REQUEST);
//        }
//        Chapter newChapter = DtoUtility.toChapter(chapterDTO);
//        chapterService.updateChapter(newChapter, chapterId);
//        courseModule.updateChapter(newChapter);
//        courseModule = courseModuleService.updateCourseModule(courseModule, courseModuleId);
//        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModule), HttpStatus.OK);
//    }
    @DeleteMapping("/deleteChapterInCourseModule/{courseModuleId}/{chapterId}")
    public ResponseEntity<CourseModuleDTO> updateChapterInCourseModule(@PathVariable Long courseModuleId, @PathVariable Long chapterId) {
        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
        if (courseModule == null) {
            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Chapter chapter = chapterService.findChapter(chapterId);
        if (chapter == null) {
            return new ResponseEntity("Chapter " + chapterId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        courseModule.deleteChapter(chapter);        // Delete in courseModule first
        chapterService.deleteChapter(chapterId);    // Than, delete it in chapterService that will reflect to database
        courseModule = courseModuleService.updateCourseModule(courseModule, courseModuleId);
        return new ResponseEntity(DtoUtility.toCourseModuleDTO(courseModule), HttpStatus.OK);
    }

    /**
     * Add a student to coursemodule, where the student must registered before he or she enroll to the course module,
     * the Grade 0 will be setted st same time
     * @param studentId an exist student
     * @param courseModuleId an exist course module
     * @return Course Module DTO
     */
    @PostMapping("/addStudent/{courseModuleId}/{studentId}")
    public ResponseEntity<CourseModuleDTO> addStudentToCourseModule(@PathVariable Long studentId, @PathVariable Long courseModuleId){
        Student student = studentService.findStudent(studentId);
        if(student == null){
            return new ResponseEntity("Student " + studentId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
        if(courseModule == null){
            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        // Add an exist student
        courseModule.addStudent(student);

        // Set default for the student
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setGradeValue(0.0);
        grade.setCourseModule(courseModule);
        grade = gradeService.saveGrade(grade);
        courseModule.addGrade(grade);

        courseModule = courseModuleService.saveCourseModule(courseModule);
        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModuleService.saveCourseModule(courseModule)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{courseModuleId}/{studentId}")
    public ResponseEntity<CourseModuleDTO> deleteStudentFromCourseModule(@PathVariable Long studentId, @PathVariable Long courseModuleId){
        Student student = studentService.findStudent(studentId);
        if(student == null){
            return new ResponseEntity("Student " + studentId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
        if(courseModule == null){
            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        // Delete grade first
        courseModule.deleteGrade(student);
        courseModule.deleteStudent(student);
        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModuleService.saveCourseModule(courseModule)), HttpStatus.OK);
    }

    @PostMapping("/updateGrade/{courseModuleId}")
    public ResponseEntity<CourseModuleDTO> updateGrade(@RequestBody GradeDTO gradeDTO, @PathVariable Long courseModuleId){
        CourseModule courseModule = courseModuleService.findCourseModule(courseModuleId);
        if(courseModule == null){
            return new ResponseEntity("CourseModel " + courseModuleId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Student student = studentService.findStudent(gradeDTO.getStudentId());
        if(student == null){
            return new ResponseEntity("Student " + gradeDTO.getStudentId() + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Grade foundGrade = null;
        for(Grade grade : courseModule.getGrades()){
            if(grade.getStudent().getId().equals(gradeDTO.getStudentId())){
                foundGrade = grade;
            }
        }
        if(foundGrade == null){
            return new ResponseEntity("Grade " + " NOT EXIST for Student " + gradeDTO.getStudentId(), HttpStatus.BAD_REQUEST);
        }
        Grade updatedGrade = gradeService.updateGrade(gradeDTO.getGradeValue(), foundGrade.getId());
        courseModule.updateGrade(updatedGrade);

        courseModule = courseModuleService.updateCourseModule(courseModule, courseModuleId);
        return new ResponseEntity<CourseModuleDTO>(DtoUtility.toCourseModuleDTO(courseModule), HttpStatus.OK);
    }
}
