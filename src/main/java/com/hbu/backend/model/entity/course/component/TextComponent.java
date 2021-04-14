package com.hbu.backend.model.entity.course.component;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TextComponent implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Nationalized
    private String title;
    @Nationalized
    private String description;
    @Nationalized
    private String inputText;
}
