package com.example.chickenmgt.controller;

import com.example.chickenmgt.model.Chicken;
import com.example.chickenmgt.service.ChickenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chickens")
@RequiredArgsConstructor
public class ChickenController {
    private final ChickenService chickenService;

    @GetMapping
    public Page<Chicken> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return chickenService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chicken> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chickenService.findById(id));
    }

    @PostMapping
    public Chicken create(@RequestBody Chicken chicken) {
        return chickenService.save(chicken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chicken> update(@PathVariable Long id, @RequestBody Chicken chicken) {
        Chicken existing = chickenService.findById(id);
        chicken.setId(existing.getId());
        return ResponseEntity.ok(chickenService.save(chicken));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chickenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
