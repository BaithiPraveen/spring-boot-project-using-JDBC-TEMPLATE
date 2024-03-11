package com.javacompany.jdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import java.util.Map;
@Setter
@Getter
public class GenericDTO {

    private String tableName;

    private Map<String,Object> payLoad;

    private String eventType;

    private Long rowId;


}
