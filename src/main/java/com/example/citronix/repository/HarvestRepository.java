package com.example.citronix.repository;

import com.example.citronix.entity.Harvest;
import com.example.citronix.entity.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    boolean existsBySeasonAndDate(Season season, LocalDate date);  // Check if a harvest exists for a specific season and date

    List<Harvest> findBySeason(Season season);  // Get all harvests by season
}
