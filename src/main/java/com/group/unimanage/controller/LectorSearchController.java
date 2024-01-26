package com.group.unimanage.controller;

import com.group.unimanage.service.LectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search/")
public class LectorSearchController {
    private final LectorService lectorService;

    public LectorSearchController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public ResponseEntity<List<String>> searchLectors(@RequestParam("query") String query) {
        List<String> lectors = lectorService.searchLectors(query);
        return ResponseEntity.ok(lectors);
    }
}
