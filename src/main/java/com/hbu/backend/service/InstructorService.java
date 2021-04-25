package com.hbu.backend.service;

import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    /**
     * create
     */
    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * delete
     */
    public void deleteInstructor(Instructor instructor) {
        if (instructor != null) {
            instructorRepository.delete(instructor);
        }
    }

    /**
     * read one
     */
    public Instructor findInstructor(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    /**
     * read all
     */
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    /**
     * update
     */
    public Instructor updateInstructor(Instructor instructor, Long id) {
        Instructor foundInstructor = instructorRepository.findById(id).orElse(null);

        if (foundInstructor == null) {
            return null;
        }

        foundInstructor.setFirstName(instructor.getFirstName());
        foundInstructor.setLastName(instructor.getLastName());
        foundInstructor.setEmail(instructor.getEmail());
        foundInstructor.setUsername(instructor.getUsername());
        foundInstructor.setPassword(instructor.getPassword());
        foundInstructor.setRoleType(instructor.getRoleType());
        foundInstructor.setCourses(instructor.getCourses());

        return instructorRepository.save(foundInstructor);
    }
}
