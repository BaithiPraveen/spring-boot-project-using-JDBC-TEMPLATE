package com.javacompany.jdbc.repository.impl;

import com.javacompany.jdbc.entity.User;
import com.javacompany.jdbc.mapper.UserRowMapper;
import com.javacompany.jdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new UserRowMapper()).get(0);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user(name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
        return user;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User updateUserById(Long id, User user) {
        String sql = "UPDATE user SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
        return user;
    }
}
