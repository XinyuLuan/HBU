package com.hbu.backend.repository.course;

import com.hbu.backend.model.entity.course.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {
}
