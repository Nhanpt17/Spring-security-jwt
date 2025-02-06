package com.human.simpleexamplespringsecurityjwt.mapper;

import com.human.simpleexamplespringsecurityjwt.dto.Roledto;
import com.human.simpleexamplespringsecurityjwt.entity.Role;
import com.human.simpleexamplespringsecurityjwt.entity.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleMapper {
    public static Role mapToRole(Roledto roledto){
        if (roledto == null) return null;
        return new Role(
                roledto.getId(),
                roledto.getName(),
                new HashSet<>() //empty set, since users should be handled separately
        );
    }

    public static Roledto mapToRoleDto(Role role){
        if (role ==null) return null;

        return new Roledto(
                role.getId(),
                role.getName(),
                Optional.ofNullable(role.getUsers())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(User::getUsername) // convert User object to usernames
                        .collect(Collectors.toSet())
        );
    }



}
