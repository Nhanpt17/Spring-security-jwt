package com.human.simpleexamplespringsecurityjwt.service;

import com.human.simpleexamplespringsecurityjwt.dto.Roledto;

import java.util.List;

public interface RoleService {
    Roledto createRole(Roledto roledto);
    List<Roledto> getAllRole();
}
