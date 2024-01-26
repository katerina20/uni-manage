package com.group.unimanage.service;

import com.group.unimanage.exception.NotFoundException;
import com.group.unimanage.model.Department;
import com.group.unimanage.model.Lector;
import com.group.unimanage.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

import static com.group.unimanage.model.Lector.Degree;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDepartments_ShouldReturnAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("1", "Math", new HashSet<>()));
        departments.add(new Department("2", "Physics", new HashSet<>()));

        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getDepartments();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void getDepartmentStatistics_ShouldReturnCorrectStatistics() {
        Set<Lector> lectors = new HashSet<>();
        lectors.add(new Lector("1", "Yuliya Shevchenko", Degree.ASSISTANT));
        lectors.add(new Lector("2", "Yaroslav Ivanenko", Degree.PROFESSOR));
        lectors.add(new Lector("3", "Sergii Prokopenko", Degree.ASSOCIATE_PROFESSOR));

        Department department = new Department("1", "Math", lectors);
        when(departmentRepository.findById("1")).thenReturn(Optional.of(department));

        Map<String, Integer> statistics = departmentService.getDepartmentStatistics("1");

        assertNotNull(statistics);
        assertEquals(1, statistics.get("ASSISTANT").intValue());
        assertEquals(1, statistics.get("ASSOCIATE_PROFESSOR").intValue());
        assertEquals(1, statistics.get("PROFESSOR").intValue());
    }

    @Test
    void getDepartmentStatistics_WithNonExistentDepartment_ShouldThrowNotFoundException() {
        when(departmentRepository.findById("nonexistent")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            departmentService.getDepartmentStatistics("nonexistent");
        });
    }
}