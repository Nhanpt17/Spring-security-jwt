package com.human.simpleexamplespringsecurityjwt.dto;

import com.human.simpleexamplespringsecurityjwt.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private Set<String> roles = new HashSet<>();
}
