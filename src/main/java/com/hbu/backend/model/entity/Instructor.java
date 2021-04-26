package com.hbu.backend.model.entity;

import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import lombok.Data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id; with error-> ** Unable to locate Attribute  with the the given name [id] on this ManagedType [com.hbu.backend.model.entity.Instructor] **
    private Long id;

    @Nationalized
    private String firstName;
    @Nationalized
    private String lastName;
    @Nationalized
    private String email;
    @Nationalized
    private String username;
    @Nationalized
    private String password;

    @OneToMany
    private List<CourseModule> courseModules;
}
