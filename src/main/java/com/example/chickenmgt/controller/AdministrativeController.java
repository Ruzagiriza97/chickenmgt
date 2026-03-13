package com.example.chickenmgt.controller;

import com.example.chickenmgt.service.LocationImportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/locations")
public class AdministrativeController {

    private final LocationImportService locationImportService;

    public AdministrativeController(LocationImportService locationImportService) {
        this.locationImportService = locationImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importLocations(@RequestBody String jsonContent) {
        try {
            locationImportService.importLocations(jsonContent);
            return ResponseEntity.ok("Locations imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error importing locations: " + e.getMessage());
        }
    }
}
