package com.hbu.backend.controller.course;

import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    String _Tag = "CourseController -> ";
    String _errorTag = "Course ";
    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO){
        log.info(_Tag + "DTO: " + courseDTO.toString());
        Course course = DtoUtility.toCourseEntity(courseDTO);
        // map to Instructor here
        // if not found the Instructor, return response false
        // log.info(_Tag + course.toString());
        Course newCourse = courseService.saveCourse(course);
        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(newCourse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id){
        Course course = courseService.findCourse(id);

        if(course == null){
            return new ResponseEntity(_errorTag + "Id " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(course), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<Course> courses = courseService.findAllCourse();

        List<CourseDTO> courseDTOs = new ArrayList<>();
        for(Course course : courses){
            courseDTOs.add(DtoUtility.toCourseDTO(course));
        }

        return new ResponseEntity<List<CourseDTO>>(courseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        Course course = courseService.updateCourse(DtoUtility.toCourseEntity(courseDTO), id);

        if(course == null){
            return new ResponseEntity(_errorTag + "Id " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CourseDTO>(DtoUtility.toCourseDTO(course), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        Course course = courseService.findCourse(id);

        if(course == null){
            return new ResponseEntity(_errorTag + "Id " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        courseService.deleteCourse(course);
        return ResponseEntity.ok("Deleted course " + id);
    }
}
