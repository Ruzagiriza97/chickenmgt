package com.example.chickenmgt.controller;

import com.example.chickenmgt.model.Location;
import com.example.chickenmgt.service.LocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.findById(id));
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody Location location) {
        Location existing = locationService.findById(id);
        location.setId(existing.getId());
        return ResponseEntity.ok(locationService.save(location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
