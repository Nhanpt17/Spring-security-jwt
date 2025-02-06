package com.human.simpleexamplespringsecurityjwt.service;

import com.human.simpleexamplespringsecurityjwt.dto.Roledto;
import com.human.simpleexamplespringsecurityjwt.entity.Role;
import com.human.simpleexamplespringsecurityjwt.mapper.RoleMapper;
import com.human.simpleexamplespringsecurityjwt.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Roledto createRole(Roledto roledto) {

        Role role = RoleMapper.mapToRole(roledto);

        role.setUsers(new HashSet<>());
        Role savedRole =roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }

    @Override
    public List<Roledto> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> RoleMapper.mapToRoleDto(role)).collect(Collectors.toList());
    }
}
