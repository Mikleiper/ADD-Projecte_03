package com.ra5.projecte3.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByRole(Role role);

    User findByUsername(String username);

    User findByEmail(String email);
}
