package com.hbu.backend.model.entity;

import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="Id")
    private Long id;

    @Nationalized
    private String studentId;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CourseModule> courseModules;


//    @JoinTable(name = "coursemodule_student_mapping",
//            joinColumns = {@JoinColumn(name = "student_Id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "coursemodule_id", referencedColumnName = "id")}
//            )
//    @MapKeyJoinColumn(name = "coursemodule_id")
//    private Map<CourseModule, Double> courseModules;


    public Student(String studentId, String firstName, String lastName, String email, String username, String password) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Student() {
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }
}
