package com.hbu.backend.model.entity.course;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String Title;

    @Nationalized
    private String classSubject;

    @Nationalized
    private String courseNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL)
    List<CourseModule> courseModules;

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
