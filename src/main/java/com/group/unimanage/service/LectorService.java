package com.group.unimanage.service;

import com.group.unimanage.model.Lector;
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
}
