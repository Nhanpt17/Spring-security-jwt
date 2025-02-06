package com.human.simpleexamplespringsecurityjwt.mapper;

import com.human.simpleexamplespringsecurityjwt.dto.Userdto;
import com.human.simpleexamplespringsecurityjwt.entity.Role;
import com.human.simpleexamplespringsecurityjwt.entity.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {
    public static User mapToUser(Userdto userdto){
        if (userdto ==null) return  null; // prevent nullpointerexception

        return new User(
                userdto.getId(),
                userdto.getUsername(),
                userdto.getPassword(),
                userdto.isEnabled(),
                new HashSet<>() // empty set, since roles shoube be handle separately
        );
    }
    public static Userdto mapToUserDto(User user){
        if (user == null) return null; // prevent nullpointerException

        return new Userdto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                Optional.ofNullable(user.getRoles())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(Role::getName)// convert Role objects to role names
                        .collect(Collectors.toSet())
        );
    }
}
