package com.hbu.backend.model.entity;

import com.hbu.backend.model.dto.UserDTO;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import lombok.Data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="instructor")

public class Instructor extends User {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<CourseModule> courseModules;

    public boolean addCourseModule(CourseModule courseModule){
        return courseModules.add(courseModule);
    }

    public boolean updateCourseModule(CourseModule courseModule){
        int i = 0;
        for(; i < courseModules.size(); i++){
            if(courseModules.get(i).getId() == courseModule.getId()){
                break;
            }
        }
        if( i == courseModules.size()){
            return false;
        }
        return true;
    }

    public void deleteCourseModule(CourseModule courseModule){

        int i = 0;
        for(; i < courseModules.size(); i++){
            if(courseModules.get(i).getId() == courseModule.getId()){
                break;
            }
        }
        if(i == courseModules.size()){
            return;
        }

        courseModules.remove(i);
    }

}
