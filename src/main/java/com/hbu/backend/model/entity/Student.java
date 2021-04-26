package com.hbu.backend.model.entity;

import com.hbu.backend.model.dto.UserDTO;
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

public class Student extends User {

    @Nationalized
    private String studentUniversityId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CourseModule> courseModules;

    // Add List Grade

//    @JoinTable(name = "coursemodule_student_mapping",
//            joinColumns = {@JoinColumn(name = "student_Id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "coursemodule_id", referencedColumnName = "id")}
//            )
//    @MapKeyJoinColumn(name = "coursemodule_id")
//    private Map<CourseModule, Double> courseModules;


    public Student() {
    }

    public boolean addCourseModule(CourseModule courseModule){
        return courseModules.add(courseModule);
    }

    public int findCourseModule(Long id){
        int i = 0;
        for(; i < courseModules.size(); i++){
            if(courseModules.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public boolean deleteCourseModule(CourseModule courseModule){
        int found = findCourseModule(courseModule.getId());
        if(found == -1){
            return false;
        }
        courseModules.remove(found);
        return true;
    }

    public boolean updateCourseModule(CourseModule courseModule){
        int found = findCourseModule(courseModule.getId());
        if(found == -1){
            return false;
        }
        courseModules.set(found, courseModule);
        return true;
    }
}
