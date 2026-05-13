package com.ra5.projecte3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getUsername(),
                        u.getFirstName(), u.getLastName(), u.getRole(), u.getDataCreated()))
                .toList();
    }

    public Optional<UserResponseDTO> findById(String id) {
        return userRepository.findById(id)
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getUsername(),
                        u.getFirstName(), u.getLastName(), u.getRole(), u.getDataCreated()));
    }

    public List<UserResponseDTO> findByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getUsername(),
                        u.getFirstName(), u.getLastName(), u.getRole(), u.getDataCreated()))
                .toList();
    }

    public Optional<UserResponseDTO> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getUsername(),
                        u.getFirstName(), u.getLastName(), u.getRole(), u.getDataCreated()));
    }
}
