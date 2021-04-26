package com.hbu.backend.repository.course;

import com.hbu.backend.model.entity.course.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findGradesByStudentId(Long studentId);
}
