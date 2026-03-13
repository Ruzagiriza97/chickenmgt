package com.example.chickenmgt.repository;

import com.example.chickenmgt.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VillageRepository extends JpaRepository<Village, Long> {
    Optional<Village> findByName(String name);
    Optional<Village> findByCode(String code);
}
