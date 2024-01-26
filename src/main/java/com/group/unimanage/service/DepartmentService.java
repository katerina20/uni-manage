package com.group.unimanage.service;

import com.group.unimanage.exception.NotFoundException;
import com.group.unimanage.model.Department;
import com.group.unimanage.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Department> getDepartments() {
        log.info("Getting all departments");
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getDepartmentStatistics(String departmentId) {
        log.info("Getting department statistics for department with ID: {}", departmentId);
        Map<String, Integer> statistics = new HashMap<>();
        Department department = departmentRepository.findById(departmentId).orElseThrow(
            () -> new NotFoundException("Department not found with ID: " + departmentId)
        );

        statistics.put("ASSISTANT", 0);
        statistics.put("ASSOCIATE_PROFESSOR", 0);
        statistics.put("PROFESSOR", 0);

        department.getLectors().forEach(lector -> {
            String degree = lector.getDegree().name();
            statistics.put(degree, statistics.get(degree) + 1);
        });

        return statistics;
    }
}
