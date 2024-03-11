package com.javacompany.jdbc.repository.impl;

import com.javacompany.jdbc.entity.User;
import com.javacompany.jdbc.mapper.UserRowMapper;
import com.javacompany.jdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class UserRepositoryImplNamedParameterJdbcTemplate implements UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return namedParameterJdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new UserRowMapper());
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user(name, email) VALUES (:name, :email)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", user.getName());
        paramMap.put("email", user.getEmail());
        namedParameterJdbcTemplate.update(sql, paramMap);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public User updateUserById(Long id, User user) {
        String sql = "UPDATE user SET name = :name, email = :email WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getId());
        paramMap.put("name", user.getName());
        paramMap.put("email", user.getEmail());
        namedParameterJdbcTemplate.update(sql, paramMap);
        return user;
    }
}
