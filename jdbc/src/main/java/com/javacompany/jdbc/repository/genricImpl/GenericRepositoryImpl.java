package com.javacompany.jdbc.repository.genricImpl;

import com.javacompany.jdbc.repository.GenericRepository;
import com.javacompany.jdbc.utils.GetClassName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericRepositoryImpl<T> implements GenericRepository<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    GetClassName  entityClass = new GetClassName();

    @Override
    public List<T> findAll(String tableName) throws ClassNotFoundException {
        String sql = "SELECT * FROM " + tableName;
        return (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass.getEntityClass(tableName)));
    }

    @Override
    public T findById(String tableName,Long id) throws ClassNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        return (T) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(entityClass.getEntityClass(tableName)));
    }

    @Override
    public void save(String tableName, Map<String, Object> payLoad) {
        String sql = "INSERT INTO "+tableName+"(name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, payLoad.get("name"), payLoad.get("email"));
    }

    @Override
    public void deleteById(String tableName,Long id) {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateById(String tableName,Long id, Map<String, Object> payLoad) {
        String sql = "UPDATE "+tableName+" SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, payLoad.get("name"), payLoad.get("email"),id);
    }


}
