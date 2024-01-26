package com.group.unimanage.controller;

import com.group.unimanage.service.LectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{lector_id}/change-name")
    public ResponseEntity<Void> changeLectorName(@PathVariable("lector_id") String lectorId,
                                                 @RequestParam("newName") String newName) {
        boolean updated = lectorService.changeLectorName(lectorId, newName);

        if (!updated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }
}
