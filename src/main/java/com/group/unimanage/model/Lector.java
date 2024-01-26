package com.group.unimanage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
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

    @JsonIgnore
    @DocumentReference(lazy=true)
    private Set<Department> departments;

    @AllArgsConstructor
    public enum Degree {
        ASSISTANT ("Assistant"),
        ASSOCIATE_PROFESSOR ("Associate Professor"),
        PROFESSOR ("Professor");

        private final String value;

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
