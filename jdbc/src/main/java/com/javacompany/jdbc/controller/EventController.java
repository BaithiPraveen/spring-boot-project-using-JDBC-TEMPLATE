package com.javacompany.jdbc.controller;

import com.javacompany.jdbc.dto.GenericDTO;
import com.javacompany.jdbc.entity.User;
import com.javacompany.jdbc.repository.GenericRepository;
import com.javacompany.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private UserService userService;
    @Autowired
    private GenericRepository<?> genericRepository;
    @PostMapping("/dml")
    public ResponseEntity<?> dmlQuery(@RequestBody GenericDTO genericDTO) throws ClassNotFoundException {

        String tableName = genericDTO.getTableName();
        String eventType = genericDTO.getEventType();
        Long rowId = genericDTO.getRowId();
        Map<String, Object> payload;

        if (tableName.equalsIgnoreCase("user") || tableName.equalsIgnoreCase("person")) {

            switch (eventType.toLowerCase()) {

                case "insert":
                    payload = genericDTO.getPayLoad();
                    genericRepository.save(tableName,payload);
                    return ResponseEntity.status(HttpStatus.CREATED).body(payload.get("name")+" created successfully");

                case "update":
                    payload = genericDTO.getPayLoad();
                    genericRepository.updateById(tableName,rowId,payload);
                    return ResponseEntity.ok(payload.get("name")+" updated successfully");

                case "delete":
                    genericRepository.deleteById(tableName,rowId);
                    return ResponseEntity.ok(rowId+" user deleted successfully");

                case "fetch":
                    Object byId = genericRepository.findById(genericDTO.getTableName(), genericDTO.getRowId());
                    return ResponseEntity.ok(byId);

                case "fetchall":
                    List<?> allObjects = genericRepository.findAll(tableName);
                    return ResponseEntity.ok(allObjects);

                default:
                    return ResponseEntity.ok("Invalid event type");
            }
        } else {
            System.out.println("Unknown table");
            return ResponseEntity.ok("Unknown table name ....!");
        }

    }
}
