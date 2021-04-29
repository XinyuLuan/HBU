package com.hbu.backend.repository.course;

import com.hbu.backend.model.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
