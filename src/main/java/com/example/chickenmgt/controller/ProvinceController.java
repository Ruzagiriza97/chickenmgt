package com.example.chickenmgt.controller;

import com.example.chickenmgt.model.Province;
import com.example.chickenmgt.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
    public List<Province> getAll() {
        return provinceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getById(@PathVariable Long id) {
        return ResponseEntity.ok(provinceService.findById(id));
    }

    @PostMapping
    public Province create(@RequestBody Province province) {
        return provinceService.save(province);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Province> update(@PathVariable Long id, @RequestBody Province province) {
        Province existing = provinceService.findById(id);
        province.setId(existing.getId());
        return ResponseEntity.ok(provinceService.save(province));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        provinceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
