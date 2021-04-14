package com.hbu.backend.model.entity.course.component;

import com.hbu.backend.model.entity.File;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class FileComponent implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Nationalized
    private String title;
    @Nationalized
    private String description;

    private List<File> files;
}
