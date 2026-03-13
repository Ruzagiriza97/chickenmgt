package com.example.chickenmgt.repository;

import com.example.chickenmgt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT u FROM Farm f JOIN f.owner u JOIN f.location l JOIN l.province p " +
            "WHERE p.code = :provinceIdentifier OR p.name = :provinceIdentifier")
    List<User> findByProvince(@Param("provinceIdentifier") String provinceIdentifier);
}
