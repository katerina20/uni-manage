package com.group.unimanage.controller;

import com.group.unimanage.service.LectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lector")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @PutMapping("/{lector_id}/promote")
    public ResponseEntity<Void> promoteLector(@PathVariable("lector_id") String lectorId) {
        boolean promoted = lectorService.promoteLector(lectorId);

        if (!promoted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }
}
