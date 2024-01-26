package com.group.unimanage.model;

import lombok.Data;

import java.util.Set;

@Data
public class Lector {
    private Long id;
    private String name;
    private Degree degree;
    private Set<Department> departments;

    public enum Degree {
        ASSISTANT,
        ASSOCIATE_PROFESSOR,
        PROFESSOR
    }
}
