package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.dto.course.ComponentType;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    protected Long id;

    @Nationalized
    protected String title;
    @Nationalized
    protected String description;
    @Enumerated(EnumType.STRING)
    protected ComponentType componentType;
}
