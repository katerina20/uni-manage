package com.group.unimanage.service;

import com.group.unimanage.exception.NotFoundException;
import com.group.unimanage.model.Lector;
import com.group.unimanage.model.Lector.Degree;
import com.group.unimanage.repository.LectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LectorService {
    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Transactional(readOnly = true)
    public List<String> searchLectors(String query) {
        log.info("Searching for lectors with query: {}", query);
        return lectorRepository.findByNameContainingIgnoreCase(query)
            .stream()
            .map(Lector::getName)
            .collect(Collectors.toList());
    }

    @Transactional
    public boolean promoteLector(String lectorId) {
        log.info("Promoting lector with ID: {}", lectorId);
        Lector lector = lectorRepository.findById(lectorId)
            .orElseThrow(() -> new NotFoundException("Lector not found with ID: " + lectorId));

        if (lector.getDegree() == Degree.PROFESSOR) {
            return false;
        }

        Degree newDegree = getNextDegree(lector.getDegree());
        lector.setDegree(newDegree);
        lectorRepository.save(lector);
        return true;
    }

    private Degree getNextDegree(Degree currentDegree) {
        return switch (currentDegree) {
            case ASSISTANT -> Degree.ASSOCIATE_PROFESSOR;
            case ASSOCIATE_PROFESSOR -> Degree.PROFESSOR;
            default -> currentDegree;
        };
    }
}
