package com.hbu.backend.model.entity;

import com.hbu.backend.model.dto.UserDTO;
import com.hbu.backend.model.entity.course.Course;
import lombok.Data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="instructor")
public class Instructor extends User {
    @OneToMany
    private List<Course> courses;
}
