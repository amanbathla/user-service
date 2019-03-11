package com.stackroute.userservices.controller;


import com.stackroute.userservices.domain.User;
import com.stackroute.userservices.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {



    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    public UserController() {
    }


     // Get  Request for getting all the user

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> userList = userService.getListOfUser();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }


    // get by id


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getAllUser(@PathVariable("id") int userId){

        User user = userService.getUserById(userId);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    // Post the request

    @PostMapping("/user")
    public ResponseEntity<User> savedUser(@RequestBody User user){

        User savedUser  = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user , @PathVariable("id") int userId){
        User updateUser  = userService.updateUser(user,userId);
        return new ResponseEntity<User>(updateUser, HttpStatus.OK);
    }



    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){

        User deleteUser =  userService.deleteUser(userId);
        return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
    }


}
