package com.hbu.backend.model.entity;

import com.hbu.backend.model.utility.RoleType;
import com.hbu.backend.model.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, unique=true)
    private RoleType roleType;

//    @ManyToMany(mappedBy="roles")
    @ElementCollection
    private List<User> users;
}
