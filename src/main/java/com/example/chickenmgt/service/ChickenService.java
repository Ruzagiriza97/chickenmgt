package com.example.chickenmgt.service;

import com.example.chickenmgt.model.Chicken;
import com.example.chickenmgt.repository.ChickenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ChickenService {
    private final ChickenRepository chickenRepository;

    public Page<Chicken> findAll(Pageable pageable) {
        return chickenRepository.findAll(pageable);
    }

    public Chicken findById(Long id) {
        return chickenRepository.findById(id).orElseThrow(() -> new RuntimeException("Chicken not found"));
    }

    public Chicken save(Chicken chicken) {
        return chickenRepository.save(chicken);
    }

    public void deleteById(Long id) {
        chickenRepository.deleteById(id);
    }
}
