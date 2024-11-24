package com.example.citronix.service;

import com.example.citronix.dto.FarmDto;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {

    FarmDto createFarm(FarmDto farmDto);
    FarmDto updateFarm(Long id, FarmDto farmDto);
    void deleteFarm(Long id);
    FarmDto getFarmById(Long id);
    List<FarmDto> getAllFarms();
    List<FarmDto> searchFarms(String location, Double minArea, Double maxArea, LocalDate creationDate);
}
