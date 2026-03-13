package com.example.chickenmgt.service;

import com.example.chickenmgt.model.Location;
import com.example.chickenmgt.repository.LocationRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
