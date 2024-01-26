package com.group.unimanage.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("lectors")
public class Lector {

    @Id
    private String id;
    private String name;
    private Degree degree;

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
