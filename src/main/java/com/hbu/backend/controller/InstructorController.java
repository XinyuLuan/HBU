package com.hbu.backend.controller;

import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * What inputs are we receiving as an instructor?
 *  inputs:
 *      grading course documents
 *      comments for student assignments
 *      creating files, modules, etc.
 *
 **/
@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final static String TAG_ = "InstructorController";

    @Autowired
    InstructorService instructorService;

    //convert entity to DTO
    private InstructorDTO convertInstructorToInstructorDTO(Instructor instructor) {
        return new InstructorDTO(instructor.getId(), instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getUsername(), instructor.getCourses());
    }

    // convert DTO to entity
    private Instructor convertInstructorDtoToInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setUsername(instructorDTO.getUsername());
        instructor.setCourses(instructorDTO.getCourses());
        return instructor;
    }

    @PostMapping
    public InstructorDTO addInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = convertInstructorDtoToInstructor(instructorDTO);
        InstructorDTO convertedInstructor;
        try {
            convertedInstructor = convertInstructorToInstructorDTO(instructorService.addInstructor(instructor));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor could not be saved", exception);
        }
        return convertedInstructor;
    }

    @GetMapping("/{id}")
    public InstructorDTO getInstructor(@PathVariable Long id) {
        Instructor instructor;
        try {
            instructor = instructorService.findInstructor(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor with id: " + id + "not found", exception);
        }
        return convertInstructorToInstructorDTO(instructor);
    }

    @GetMapping
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = instructorService.findAllInstructors();

        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for(Instructor instructor : instructors) {
            instructorDTOs.add(convertInstructorToInstructorDTO(instructor));
        }
        return instructorDTOs;
    }

    @PutMapping("/{id}")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long id) {
        Instructor instructor;
        try {
            instructor = instructorService.updateInstructor(convertInstructorDtoToInstructor(instructorDTO), id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor object is null", exception);
        }
        return convertInstructorToInstructorDTO(instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findInstructor(id);

        if (instructor == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "instructor " + id + " does not exist");
        }

        instructorService.deleteInstructor(instructor);
        return new ResponseEntity("Deleted " + id, HttpStatus.OK);
    }

    // add class & drop class functionalities reserved for admin only?

    // view course schedule

    // get all instructed courses

    // view class registration page

    // update changes to class schedule

    // update credentials

    // login to dashboard

    // view course roster grades

    // grade document

    // add feedback notes to documents

    // create document

    // create module

    // create media (multimedia)
}
