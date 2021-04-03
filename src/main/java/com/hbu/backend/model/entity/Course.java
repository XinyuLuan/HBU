package com.hbu.backend.model.entity;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long courseId;

    @Nationalized
    private String Title;

    @Nationalized
    private String classSubject;

    @ManyToOne
    private Instructor instructor;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
