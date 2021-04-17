package com.hbu.backend.model.entity.course;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Nationalized
    private String title;
    @Nationalized
    private String sectionType;



    @OneToMany
    private List<Component> components;
}
