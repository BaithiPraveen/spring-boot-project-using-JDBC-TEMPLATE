package com.javacompany.jdbc.controller;

import com.javacompany.jdbc.entity.User;
import com.javacompany.jdbc.repository.GenericRepository;
import com.javacompany.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private GenericRepository<User> genericRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
//        return (User) genericRepository.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@RequestBody User user,@PathVariable("id") Long id){
        return userService.updateUserById(id,user);
    }
}
