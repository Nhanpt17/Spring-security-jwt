package com.human.simpleexamplespringsecurityjwt.dto;

import com.human.simpleexamplespringsecurityjwt.entity.User;
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
public class Roledto {

    private Long id;
    private String name;
    private Set<String> usernames = new HashSet<>();
}
