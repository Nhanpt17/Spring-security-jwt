package com.human.simpleexamplespringsecurityjwt.controller;

import com.human.simpleexamplespringsecurityjwt.dto.Userdto;
import com.human.simpleexamplespringsecurityjwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    //constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Userdto> createUser(@RequestBody Userdto userdto){
        Userdto savedUser = userService.createUser(userdto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Userdto>> getAllUser(){
        List<Userdto> userdtoList = userService.getAllUsers();
        return ResponseEntity.ok(userdtoList);
    }



}
