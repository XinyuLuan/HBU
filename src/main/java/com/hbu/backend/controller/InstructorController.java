package com.hbu.backend.controller;

import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.utility.DtoUtility;
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

    @PostMapping
    public ResponseEntity<InstructorDTO> addInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = DtoUtility.toInstructor(instructorDTO);
        Instructor newInstructor = instructorService.addInstructor(instructor);
        return new ResponseEntity<InstructorDTO>(DtoUtility.toInstructorDTO(newInstructor), HttpStatus.OK);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable long instructorId) {
        Instructor instructor = instructorService.findInstructor(instructorId);

        if(instructor == null){
            return new ResponseEntity("Instructor " + instructorId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<InstructorDTO>(DtoUtility.toInstructorDTO(instructor), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(){
        List<Instructor> instructors = instructorService.findAllInstructors();

        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for(Instructor instructor : instructors){
            instructorDTOs.add(DtoUtility.toInstructorDTO(instructor));
        }

        return new ResponseEntity<List<InstructorDTO>>(instructorDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long id){
        Instructor instructor = instructorService.updateInstructor(DtoUtility.toInstructor(instructorDTO), id);

        if(instructor == null){
            return new ResponseEntity("Instructor " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<InstructorDTO>(DtoUtility.toInstructorDTO(instructor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findInstructor(id);

        if(instructor == null){
            return new ResponseEntity("Instructor " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        instructorService.deleteInstructor(instructor);
        return new ResponseEntity("Deleted Instructor " + id, HttpStatus.OK);
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
