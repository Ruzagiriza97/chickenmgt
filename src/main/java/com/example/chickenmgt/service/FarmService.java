package com.example.chickenmgt.service;

import com.example.chickenmgt.model.Farm;
import com.example.chickenmgt.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;

    public List<Farm> findAll() {
        return farmRepository.findAll();
    }

    public Farm findById(Long id) {
        return farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
    }

    public Farm save(Farm farm) {
        return farmRepository.save(farm);
    }

    public void deleteById(Long id) {
        farmRepository.deleteById(id);
    }
}
