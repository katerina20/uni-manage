package com.group.unimanage.repository;

import com.group.unimanage.model.Lector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends MongoRepository<Lector, String> {
    List<Lector> findByNameContainingIgnoreCase(String name);
}
