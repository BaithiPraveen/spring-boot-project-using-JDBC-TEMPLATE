package com.javacompany.jdbc.repository;

import com.javacompany.jdbc.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User updateUserById(Long id, User user);
}

