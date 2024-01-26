package com.group.unimanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("departments")
public class Department {

    @Id
    private String id;
    private String name;

    @DocumentReference(lazy=true)
    private Set<Lector> lectors;
}
