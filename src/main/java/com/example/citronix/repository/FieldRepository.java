package com.example.citronix.repository;

import com.example.citronix.entity.Farm;
import com.example.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long>{
    List<Field> findByFarm(Farm farm);
    Long countByFarm(Farm farm);
    double sumAreaByFarm(Farm farm);
}