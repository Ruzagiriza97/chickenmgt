package com.example.chickenmgt.controller;

import com.example.chickenmgt.model.Farm;
import com.example.chickenmgt.service.FarmService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {
    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping
    public List<Farm> getAll() {
        return farmService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.findById(id));
    }

    @PostMapping
    public Farm create(@RequestBody Farm farm) {
        return farmService.save(farm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> update(@PathVariable Long id, @RequestBody Farm farm) {
        Farm existing = farmService.findById(id);
        farm.setId(existing.getId());
        return ResponseEntity.ok(farmService.save(farm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        farmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
