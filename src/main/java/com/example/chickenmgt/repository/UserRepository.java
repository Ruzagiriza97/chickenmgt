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

    @Query("SELECT u FROM User u WHERE u.village.cell.sector.district.province.code = :identifier OR u.village.cell.sector.district.province.name = :identifier")
    List<User> findByProvince(@Param("identifier") String identifier);

    @Query("SELECT u FROM User u WHERE u.village.cell.sector.district.code = :identifier OR u.village.cell.sector.district.name = :identifier")
    List<User> findByDistrict(@Param("identifier") String identifier);

    @Query("SELECT u FROM User u WHERE u.village.cell.sector.code = :identifier OR u.village.cell.sector.name = :identifier")
    List<User> findBySector(@Param("identifier") String identifier);

    @Query("SELECT u FROM User u WHERE u.village.cell.code = :identifier OR u.village.cell.name = :identifier")
    List<User> findByCell(@Param("identifier") String identifier);

    @Query("SELECT u FROM User u WHERE u.village.code = :identifier OR u.village.name = :identifier")
    List<User> findByVillage(@Param("identifier") String identifier);
}
