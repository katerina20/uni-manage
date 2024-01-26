package com.group.unimanage.service;

import com.group.unimanage.exception.NotFoundException;
import com.group.unimanage.model.Lector;
import com.group.unimanage.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static com.group.unimanage.model.Lector.Degree;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LectorServiceTest {

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorService lectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchLectors_ShouldReturnMatchingLectors() {
        when(lectorRepository.findByNameContainingIgnoreCase("iy"))
            .thenReturn(List.of(new Lector("1", "Yuliya Shevchenko", Degree.ASSISTANT)));

        List<String> result = lectorService.searchLectors("iy");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Yuliya Shevchenko", result.get(0));
    }

    @Test
    void changeLectorName_ShouldChangeNameWhenLectorExists() {
        Lector lector = new Lector("1", "Yaroslav Ivanenko", Degree.ASSISTANT);
        when(lectorRepository.findById("1")).thenReturn(Optional.of(lector));

        boolean result = lectorService.changeLectorName("1", "Yaroslav Ivanen");

        assertTrue(result);
        verify(lectorRepository, times(1)).save(lector);
        assertEquals("Yaroslav Ivanen", lector.getName());
    }

    @Test
    void changeLectorName_ShouldReturnFalseWhenLectorDoesNotExist() {
        when(lectorRepository.findById("2")).thenReturn(Optional.empty());

        boolean result = lectorService.changeLectorName("2", "Yaroslav Ivanen");

        assertFalse(result);
    }

    @Test
    void promoteLector_ShouldPromoteWhenNotProfessor() {
        Lector lector = new Lector("1", "Yaroslav Ivanenko", Degree.ASSISTANT);
        when(lectorRepository.findById("1")).thenReturn(Optional.of(lector));

        boolean result = lectorService.promoteLector("1");

        assertTrue(result);
        assertEquals(Degree.ASSOCIATE_PROFESSOR, lector.getDegree());
    }

    @Test
    void promoteLector_ShouldNotPromoteWhenProfessor() {
        Lector lector = new Lector("1", "Yaroslav Ivanenko", Degree.PROFESSOR);
        when(lectorRepository.findById("1")).thenReturn(Optional.of(lector));

        boolean result = lectorService.promoteLector("1");

        assertFalse(result);
    }

    @Test
    void promoteLector_ShouldThrowNotFoundExceptionWhenLectorDoesNotExist() {
        when(lectorRepository.findById("2")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            lectorService.promoteLector("2");
        });
    }
}