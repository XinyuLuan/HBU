package com.hbu.backend.service.course;

import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    /**
     * Create
     */
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
    /**
     * Read One
     */
    public Course findCourse(Long courseId){
        return courseRepository.findById(courseId).orElse(null);
    }

    /**
     * Read All
     */
    public List<Course> findAllCourse(){
        return courseRepository.findAll();
    }

    /**
     * Update
     */
    public Course updateCourse(Course course, Long courseId){
        Course foundCourse = courseRepository.findById(courseId).orElse(null);

        if(foundCourse == null){
            return foundCourse;
        }

        foundCourse.setTitle(course.getTitle());
//        foundCourse.setInstructor(course.getInstructor());
        foundCourse.setClassSubject(course.getClassSubject());
//        foundCourse.setStartTime(course.getStartTime());
//        foundCourse.setEndTime(course.getEndTime());
        return courseRepository.save(foundCourse);
    }

    /**
     * Delete
     */
    public void deleteCourse(Course course){
        if(course != null){
            courseRepository.delete(course);
        }
    }
}
