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
@Document("lectors")
public class Lector {

    @Id
    private String id;
    private String name;
    private Degree degree;

    @DocumentReference(lazy=true)
    private Set<Department> departments;

    public enum Degree {
        ASSISTANT,
        ASSOCIATE_PROFESSOR,
        PROFESSOR
    }
}
