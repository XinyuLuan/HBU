package com.hbu.backend.service;

import com.hbu.backend.model.entity.Student;
import com.hbu.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    // contains: CourseService, MessageService, WebService?
    @Autowired
    StudentRepository studentRepository;

    /**
     * create a student
     */
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * delete a student
     */
    public void deleteStudent(Student student) {
        if(student != null) {
            studentRepository.delete(student);
        }
    }

    /**
     * read one
     */
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * read all
     */
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * update a student
     */
    public Student updateStudent(Student student, Long id) {
        Student foundStudent = studentRepository.findById(id).orElse(null);

        if (foundStudent == null) {
            return null;
        }

        foundStudent.setStudentUniversityId(student.getStudentUniversityId());
        foundStudent.setFirstName(student.getFirstName());
        foundStudent.setLastName(student.getLastName());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setUsername(student.getUsername());
        foundStudent.setPassword(student.getPassword());
        foundStudent.setRoleType(student.getRoleType());
        foundStudent.setCourseModules(student.getCourseModules());

        return studentRepository.save(foundStudent);
    }

//    public StudentTranscriptDTO getUnofficalTranscript(String studentId, int id){
//        /*
//        ....
//         */
//        return new StudentTranscriptDTO();
//    }

}
