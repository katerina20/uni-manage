package com.group.unimanage.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class Department {

    @Id
    private String id;
    private String name;
    private Set<Lector> lectors;
}
