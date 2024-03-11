package com.javacompany.jdbc.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GenericRepository<T> {

    List<T> findAll(String tableName) throws ClassNotFoundException;
    T findById(String tableName,Long id) throws ClassNotFoundException;
    void save(String tableName, Map<String, Object> entity);
    void deleteById(String tableName,Long id);
    void updateById(String tableName,Long id, Map<String, Object> entity);

}
