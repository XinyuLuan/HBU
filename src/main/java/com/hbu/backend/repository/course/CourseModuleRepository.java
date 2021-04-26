package com.hbu.backend.repository.course;

import com.hbu.backend.model.entity.course.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {
    List<CourseModule> findCourseModulesByInstructorId(Long instructorId);
}
