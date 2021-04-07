package com.hbu.backend.model.entity;

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
    private Long Id;

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
    private List<Course> courses;
}
