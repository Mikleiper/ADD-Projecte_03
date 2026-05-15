package com.ra5.projecte3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.mapper.UserMapper;
import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public UserResponseDTO findById(String id) {
        return userRepository.findById(id).map(UserMapper::toDto).orElse(null);
    }

    public List<UserResponseDTO> findByRole(Role role) {
        return userRepository.findByRole(role).stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public UserResponseDTO findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toDto).orElse(null);
    }

}
