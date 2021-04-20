package com.hbu.backend.service.course;

import com.hbu.backend.model.dto.course.CourseModuleDTO;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.CourseModule;
import com.hbu.backend.repository.course.CourseModuleRepository;
import com.hbu.backend.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseModuleService {
    @Autowired
    CourseModuleRepository courseModuleRepository;

    /**
     * Create
     */
    public CourseModule saveCourseModule(CourseModule courseModule){
        return courseModuleRepository.save(courseModule);
    }

    /**
     * Read
     */
    public CourseModule findCourseModule(Long id){
        return courseModuleRepository.findById(id).orElse(null);
    }

    public List<CourseModule> findAllCourseModule(){
        return courseModuleRepository.findAll();
    }

    public CourseModule updateCourseModule(CourseModule courseModule, Long id){
        CourseModule foundCourseModule = courseModuleRepository.findById(id).orElse(null);

        if(foundCourseModule == null){
            return null;
        }

        foundCourseModule.setCourse(courseModule.getCourse());
        foundCourseModule.setInstructor(courseModule.getInstructor());
        foundCourseModule.setChapters(courseModule.getChapters());
//        foundCourseModule.setStudentToScore(courseModule.getStudentToScore());
        foundCourseModule.setStudents(courseModule.getStudents());
        foundCourseModule.setGrades(courseModule.getGrades());
        return courseModuleRepository.save(foundCourseModule);
    }

    public void deleteCourseModule(Long id){
        CourseModule foundCourseModule = courseModuleRepository.findById(id).orElse(null);

        if(foundCourseModule == null){
            return;
        }

        courseModuleRepository.delete(foundCourseModule);
    }
}
