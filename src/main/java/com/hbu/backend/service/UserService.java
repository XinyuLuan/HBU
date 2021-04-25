package com.hbu.backend.service;

import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.User;
import com.hbu.backend.model.utility.RoleType;
import com.hbu.backend.repository.AdminRepository;
import com.hbu.backend.repository.InstructorRepository;
import com.hbu.backend.repository.StudentRepository;
import com.hbu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    StudentRepository studentRepository;

    /**
     * create a user
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * delete a user
     */
    public void deleteUser(User user) {
        if(user != null) {
            userRepository.delete(user);
        }
    }

    /**
     * read one
     */
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * read all
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * update a user
     */
    public User updateUser(User user, Long id) {

//        List<RoleType> roles = user.getRoles();
//        User foundUser = userRepository.findById(id).orElse(null);
//
//        if (foundUser == null) {
//            return null;
//        }
//
//        foundUser.setFirstName(user.getFirstName());
//        foundUser.setLastName(user.getLastName());
//        foundUser.setEmail(user.getEmail());
//        foundUser.setUsername(user.getUsername());
//        foundUser.setPassword(user.getPassword());
//        foundUser.setRoles(user.getRoles());
//
//        if (roles.contains("ADMIN")) {
//            Admin admin = adminRepository.findById(id).orElse(null);
//            admin.setRoles(foundUser.getRoles());
//            adminRepository.save(admin);
//        }
//        if (roles.contains("INSTRUCTOR")) {
//            Instructor instructor = instructorRepository.findById(id).orElse(null);
//            instructor.setRoles(foundUser.getRoles());
//            instructorRepository.save(instructor);
//        }
//        if (roles.contains("STUDENT")) {
//            Student student = studentRepository.findById(id).orElse(null);
//            student.setRoles(foundUser.getRoles());
//            studentRepository.save(student);
//        }
//
//        return userRepository.save(foundUser);

        User foundUser = userRepository.findById(id).orElse(null);

        if (foundUser == null) {
            return null;
        }

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setEmail(user.getEmail());
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setRoleType(user.getRoleType());

        return userRepository.save(foundUser);
    }
}
