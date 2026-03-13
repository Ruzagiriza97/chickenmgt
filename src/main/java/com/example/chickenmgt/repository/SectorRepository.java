package com.example.chickenmgt.repository;

import com.example.chickenmgt.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findByName(String name);
    Optional<Sector> findByCode(String code);
}
