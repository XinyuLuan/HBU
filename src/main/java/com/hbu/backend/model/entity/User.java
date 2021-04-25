package com.hbu.backend.model.entity;

import com.hbu.backend.model.utility.RoleType;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="user")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Nationalized
//    @Column(nullable=false)
    private String firstName;

    @Nationalized
//    @Column(nullable=false)
    private String lastName;

    @Nationalized
//    @Column(nullable=false, unique=true)
    private String email;

    @Nationalized
//    @Column(nullable=false, unique=true)
    private String username;

    @Nationalized
//    @Column(nullable=false)
//    @Size(min=4)
    private String password;

//    @ManyToMany(cascade=CascadeType.MERGE)
//    @JoinTable(
//            name="users_roles",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
//    private List<Role> roles;

//    @ElementCollection
//    private List<RoleType> roles;

    private RoleType roleType;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, RoleType roleType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.roleType = roleType;
    }
}
