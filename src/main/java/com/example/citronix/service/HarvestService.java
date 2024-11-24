package com.example.citronix.service;

import com.example.citronix.dto.HarvestDto;

import java.util.List;

public interface HarvestService {

    HarvestDto createHarvest(HarvestDto harvestDto);
    void deleteHarvest(Long id);
    HarvestDto getHarvestById(Long id);
    List<HarvestDto> getAllHarvests();
    List<HarvestDto> getHarvestsBySeason(String season); // New method to filter by season
}
