package com.hbu.backend.service.course;

import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.Grade;
import com.hbu.backend.repository.StudentRepository;
import com.hbu.backend.repository.course.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;

    public Grade saveGrade(Grade grade){
        return gradeRepository.save(grade);
    }

    public Grade findGrade(Long id){
        return gradeRepository.findById(id).orElse(null);
    }
    public List<Grade> findAllGrade(){
        return gradeRepository.findAll();
    }

    public Grade updateGrade(Double newGrade, Long id){
        Grade grade = gradeRepository.findById(id).orElse(null);

        if(grade == null){
            return null;
        }

        grade.setGradeValue(newGrade);
        return gradeRepository.save(grade);
    }

    public boolean deleteGrade(Long id){
        Grade grade = gradeRepository.findById(id).orElse(null);

        if(grade == null){
            return false;
        }

        gradeRepository.delete(grade);
        return true;
    }

    public List<Grade> findGradesByStudentId(Long studentId){
        return gradeRepository.findGradesByStudentId(studentId);
    }

}
