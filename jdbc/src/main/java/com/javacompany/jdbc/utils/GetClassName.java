package com.javacompany.jdbc.utils;

import com.javacompany.jdbc.dto.GenericDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Map;

public class GetClassName {

    public Class<?> getEntityClass(String tableName) throws ClassNotFoundException {
        return Class.forName("com.javacompany.jdbc.entity." + tableName.substring(0, 1).toUpperCase() + tableName.substring(1));
    }

//    public MapSqlParameterSource extractParameters(T entity) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        // Assuming the entity is an instance of GenericDTO
//        if (entity instanceof GenericDTO) {
//            GenericDTO genericDTO = (GenericDTO) entity;
//            // Assuming the payload contains key-value pairs of column names and values
//            Map<String, Object> payload = genericDTO.getPayLoad();
//            // Add each key-value pair to the parameters
//            for (Map.Entry<String, Object> entry : payload.entrySet()) {
//                params.addValue(entry.getKey(), entry.getValue());
//            }
//        }
//        return params;
//    }
}
