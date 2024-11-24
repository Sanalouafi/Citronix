package com.example.citronix.repository;

import com.example.citronix.entity.Harvest;
import com.example.citronix.entity.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    boolean existsBySeasonAndDate(Season season, LocalDate date);
}
