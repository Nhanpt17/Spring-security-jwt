package com.human.simpleexamplespringsecurityjwt.service;

import com.human.simpleexamplespringsecurityjwt.dto.Userdto;
import com.human.simpleexamplespringsecurityjwt.repository.UserRepository;

import java.util.List;

public interface UserService {
    Userdto createUser(Userdto userdto);
    List<Userdto> getAllUsers();
}
