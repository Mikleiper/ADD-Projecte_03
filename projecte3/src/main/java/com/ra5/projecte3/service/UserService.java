package com.ra5.projecte3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.mapper.UserMapper;
import com.ra5.projecte3.model.AcademicProfile;
import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.model.User;
import com.ra5.projecte3.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserResponseDTO findById(String id) {
        return userRepository.findById(id).map(userMapper::toDto).orElse(null);
    }

    public List<UserResponseDTO> findByRole(Role role) {
        return userRepository.findByRole(role).stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserResponseDTO findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDto).orElse(null);
    }

    public UserResponseDTO create(UserRequestDTO request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return null; 
        }
        User userEntity = userMapper.toEntity(request);
        User savedUser = userRepository.save(userEntity);

        return userMapper.toDto(savedUser);
    }

    public UserResponseDTO update(String id, UserRequestDTO request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null; 
        }

        User userToUpdate = optionalUser.get();

        userToUpdate.setFirstName(request.getFirstName());
        userToUpdate.setLastName(request.getLastName());
        userToUpdate.setEmail(request.getEmail());
        userToUpdate.setUsername(request.getUsername());
        userToUpdate.setPassword(request.getPassword());
        userToUpdate.setRole(request.getRole());

        if (request.getGrade() != null || request.getCourse() != null || request.getObservations() != null) {
            AcademicProfile profile = userToUpdate.getAcademicProfile();
            if (profile == null) {
                profile = new AcademicProfile();
                profile.setStatus("ACTIVE");
            }
            profile.setGrade(request.getGrade());
            profile.setCourse(request.getCourse());
            profile.setObservations(request.getObservations());
            
            userToUpdate.setAcademicProfile(profile);
        }

        User updatedUser = userRepository.save(userToUpdate);

        return userMapper.toDto(updatedUser);
    }

    public boolean delete(String id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }


}
