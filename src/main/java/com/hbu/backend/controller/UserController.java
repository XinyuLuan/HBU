package com.hbu.backend.controller;

import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.dto.UserDTO;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.User;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static String TAG_ = "UserController";

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = DtoUtility.toUser(userDTO);
        User newUser = userService.addUser(user);
        return new ResponseEntity<UserDTO>(DtoUtility.toUserDTO(newUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        User user = userService.findUser(id);

        if (user == null) {
            return new ResponseEntity("User " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(DtoUtility.toUserDTO(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user : users) {
            userDTOs.add(DtoUtility.toUserDTO(user));
        }

        return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        User user = userService.updateUser(DtoUtility.toUser(userDTO), id);

        if(user == null){
            return new ResponseEntity("User " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(DtoUtility.toUserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        User user = userService.findUser(id);

        if(user == null){
            return new ResponseEntity("User " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        userService.deleteUser(user);
        return new ResponseEntity("Deleted User " + id, HttpStatus.OK);
    }
}
