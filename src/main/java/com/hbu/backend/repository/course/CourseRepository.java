package com.hbu.backend.repository.course;

import com.hbu.backend.model.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
