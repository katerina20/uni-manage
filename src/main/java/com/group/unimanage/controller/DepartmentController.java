package com.group.unimanage.controller;

import com.group.unimanage.model.Department;
import com.group.unimanage.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{department_id}/statistics")
    public ResponseEntity<Map<String, Integer>> getDepartmentStatistics(@PathVariable("department_id") String departmentId) {
        Map<String, Integer> statistics = departmentService.getDepartmentStatistics(departmentId);
        return ResponseEntity.ok(statistics);
    }
}
