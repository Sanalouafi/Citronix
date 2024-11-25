package com.example.citronix.repository;

import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long>{
    Optional<Field> findById(Long id);
    List<Field> findByFarm(Farm farm);
    int countByFarm(Farm farm);
    @Query("SELECT SUM(f.area) FROM Field f WHERE f.farm = :farm")
    Double sumAreaByFarm(Farm farm);

}