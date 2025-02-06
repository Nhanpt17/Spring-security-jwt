package com.human.simpleexamplespringsecurityjwt.service;

import com.human.simpleexamplespringsecurityjwt.entity.Role;
import com.human.simpleexamplespringsecurityjwt.entity.User;
import com.human.simpleexamplespringsecurityjwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
//        System.out.println(user.getUsername()+":"+user.getPassword()+":"+user.getRoles().stream().map(Role::getName)
//                .collect(Collectors.toList()));

        user.getRoles().size();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())) // No "ROLE_" prefix added
                        .collect(Collectors.toList()))
                .build();
    }
}
