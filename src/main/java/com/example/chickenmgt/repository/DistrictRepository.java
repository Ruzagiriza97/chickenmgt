package com.example.chickenmgt.repository;

import com.example.chickenmgt.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
    Optional<District> findByCode(String code);
}
