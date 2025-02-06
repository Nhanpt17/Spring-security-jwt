package com.human.simpleexamplespringsecurityjwt.service;

import com.human.simpleexamplespringsecurityjwt.dto.Userdto;
import com.human.simpleexamplespringsecurityjwt.entity.Role;
import com.human.simpleexamplespringsecurityjwt.entity.User;
import com.human.simpleexamplespringsecurityjwt.mapper.UserMapper;
import com.human.simpleexamplespringsecurityjwt.repository.RoleRepository;
import com.human.simpleexamplespringsecurityjwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    // constructor injection
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Override
    public Userdto createUser(Userdto userdto) {
//        User user = UserMapper.mapToUser(userdto);
//
//        //User user = userRepository.save(UserMapper.mapToUser(userdto));
//        if (user.getRoles() ==null || user.getRoles().isEmpty()){
//            Role defaultRole = roleRepository.findByName("ROLE_USER")
//                                            .orElseThrow(()->new RuntimeException("Default role not found"));
//            user.setRoles(new HashSet<>());
//            user.getRoles().add(defaultRole);
//        }
//
//        User savedUser = userRepository.save(user);
//        return UserMapper.mapToUserDto(user);
        User user = UserMapper.mapToUser(userdto);

        //User user = userRepository.save(UserMapper.mapToUser(userdto));
        if (userdto.getRoles().isEmpty() || userdto.getRoles() == null){
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(()->new RuntimeException("Default role not found"));
            user.setRoles(new HashSet<>());
            user.getRoles().add(defaultRole);
        }
        else{

            user.setRoles(new HashSet<>());
            for (String role: userdto.getRoles()){
                Role role1 = roleRepository.findByName(role).orElseThrow(()->new RuntimeException("Role not found"));

                user.getRoles().add(role1);
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<Userdto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }
}
