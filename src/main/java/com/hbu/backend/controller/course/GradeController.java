package com.hbu.backend.controller.course;

import com.hbu.backend.model.dto.course.GradeDTO;
import com.hbu.backend.model.entity.course.Grade;
import com.hbu.backend.service.StudentService;
import com.hbu.backend.service.course.GradeService;
import com.hbu.backend.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<GradeDTO> addGrade(@RequestBody GradeDTO gradeDTO){
        Grade grade = new Grade();

        grade.setGradeValue(gradeDTO.getGradeValue());
        grade.setStudent(studentService.findStudent(Long.parseLong(gradeDTO.getStudentId())));

        return new ResponseEntity<GradeDTO>(DtoUtility.toGradeDTO(gradeService.saveGrade(grade)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades(){
        return new ResponseEntity<List<Grade>>(gradeService.findAllGrade(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id){
        Grade grade = gradeService.findGrade(id);

        if(grade == null){
            return new ResponseEntity("Grade " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<GradeDTO>(DtoUtility.toGradeDTO(grade), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGrade(@PathVariable Long id){
        Grade grade = gradeService.findGrade(id);

        if(grade == null){
            return new ResponseEntity("Grade " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        gradeService.deleteGrade(id);
        return new ResponseEntity("Grade " + id + " DELETE SUCCESSFUL", HttpStatus.OK);
    }
}
