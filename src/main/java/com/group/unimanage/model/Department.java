package com.group.unimanage.model;

import lombok.Data;

import java.util.Set;

@Data
public class Department {
    private Long id;
    private String name;
    private Set<Lector> lectors;
}
