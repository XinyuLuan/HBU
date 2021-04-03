package com.hbu.backend.model.entity;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long adminId;

    @Nationalized
    private String firstName;

    @Nationalized
    private String lastName;
}
