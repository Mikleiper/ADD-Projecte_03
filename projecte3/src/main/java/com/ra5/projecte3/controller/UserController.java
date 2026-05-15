package com.ra5.projecte3.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id) {
        UserResponseDTO user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> findByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.findByRole(role));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) {
       UserResponseDTO user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO createdUser = userService.create(request);
        
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO request) {
        UserResponseDTO updatedUser = userService.update(id, request);
        
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(updatedUser); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deleted = userService.delete(id);
        
        if (!deleted) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.noContent().build();
    }


}
