package com.human.simpleexamplespringsecurityjwt.controller;

import com.human.simpleexamplespringsecurityjwt.dto.Roledto;
import com.human.simpleexamplespringsecurityjwt.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Roledto> createRole(@RequestBody Roledto roledto){
        Roledto savedRole = roleService.createRole(roledto);

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Roledto>> getAllRole(){
        List<Roledto> roledtoList = roleService.getAllRole();
        return ResponseEntity.ok(roledtoList);
    }


}
