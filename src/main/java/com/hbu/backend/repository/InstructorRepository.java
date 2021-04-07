package com.hbu.backend.repository;

import com.hbu.backend.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/* similar to mapper classes where sql statements are being used */
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
