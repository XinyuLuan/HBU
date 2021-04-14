package com.hbu.backend.model.entity.course;

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

    @Nationalized
    private String courseNumber;

//    @ManyToOne
//    private Instructor instructor;
//
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
}
