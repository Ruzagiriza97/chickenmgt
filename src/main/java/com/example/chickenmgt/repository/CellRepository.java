package com.example.chickenmgt.repository;

import com.example.chickenmgt.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CellRepository extends JpaRepository<Cell, Long> {
    Optional<Cell> findByName(String name);
    Optional<Cell> findByCode(String code);
}
