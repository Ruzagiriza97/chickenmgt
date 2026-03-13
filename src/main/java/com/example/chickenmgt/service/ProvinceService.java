package com.example.chickenmgt.service;

import com.example.chickenmgt.model.Province;
import com.example.chickenmgt.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    public Province findById(Long id) {
        return provinceRepository.findById(id).orElseThrow(() -> new RuntimeException("Province not found"));
    }

    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    public void deleteById(Long id) {
        provinceRepository.deleteById(id);
    }
}
