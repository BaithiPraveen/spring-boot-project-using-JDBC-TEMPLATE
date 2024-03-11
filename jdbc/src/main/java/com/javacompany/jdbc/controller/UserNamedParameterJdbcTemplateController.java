package com.javacompany.jdbc.controller;

import com.javacompany.jdbc.entity.User;
import com.javacompany.jdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("users/npjt")
public class UserNamedParameterJdbcTemplateController {

    @Autowired
    @Qualifier("userRepositoryImplNamedParameterJdbcTemplate")
    private UserRepository userRepository;
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@RequestBody User user,@PathVariable("id") Long id) {
        return userRepository.updateUserById(id,user);
    }
}
