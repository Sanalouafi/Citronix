package com.example.citronix.service.impl;

import com.example.citronix.dto.HarvestDetailDto;
import com.example.citronix.dto.HarvestDto;
import com.example.citronix.entity.Harvest;
import com.example.citronix.entity.HarvestDetail;
import com.example.citronix.exception.customException.HarvestLimitExceededException;
import com.example.citronix.mapper.HarvestMapper;
import com.example.citronix.repository.HarvestRepository;
import com.example.citronix.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {
    @Autowired
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

    @Override
    public HarvestDto createHarvest(HarvestDto harvestDto) {
        boolean exists = harvestRepository.existsBySeasonAndDate(harvestDto.getSeason(), harvestDto.getDate());
        if (exists) {
            throw new HarvestLimitExceededException("A harvest for this season already exists.");
        }

        Harvest harvest = harvestMapper.toEntity(harvestDto);
        harvest.setTotalQuantity(
                harvestDto.getHarvestDetails().stream().mapToDouble(HarvestDetailDto::getQuantity).sum()
        );
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }

    @Override
    public void deleteHarvest(Long id) {
        harvestRepository.deleteById(id);
    }

    @Override
    public HarvestDto getHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found."));
        return harvestMapper.toDto(harvest);
    }

    @Override
    public List<HarvestDto> getAllHarvests() {
        return harvestRepository.findAll().stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }
}
