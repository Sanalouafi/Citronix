package com.example.citronix.repository;

import com.example.citronix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query("SELECT f FROM Farm f WHERE " +
            "(:location IS NULL OR f.location = :location) AND " +
            "(:minArea IS NULL OR f.area >= :minArea) AND " +
            "(:maxArea IS NULL OR f.area <= :maxArea) AND " +
            "(:creationDate IS NULL OR f.creationDate = :creationDate)")
    List<Farm> searchFarms(
            @Param("location") String location,
            @Param("minArea") Double minArea,
            @Param("maxArea") Double maxArea,
            @Param("creationDate") LocalDate creationDate);
}
