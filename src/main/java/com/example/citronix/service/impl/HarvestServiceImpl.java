package com.example.citronix.service.impl;

import com.example.citronix.dto.HarvestDto;
import com.example.citronix.dto.HarvestDetailDto;
import com.example.citronix.entity.Harvest;
import com.example.citronix.entity.HarvestDetail;
import com.example.citronix.entity.Tree;
import com.example.citronix.exception.customException.HarvestLimitExceededException;
import com.example.citronix.exception.customException.TreeNotInSeasonException;
import com.example.citronix.mapper.HarvestMapper;
import com.example.citronix.repository.HarvestRepository;
import com.example.citronix.repository.TreeRepository;
import com.example.citronix.service.HarvestService;
import com.example.citronix.entity.enums.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final HarvestMapper harvestMapper;

    @Override
    public HarvestDto createHarvest(HarvestDto harvestDto) {
        // Ensure only one harvest per season
        boolean exists = harvestRepository.existsBySeasonAndDate(harvestDto.getSeason(), harvestDto.getDate());
        if (exists) {
            throw new HarvestLimitExceededException("A harvest for this season already exists.");
        }

        // Check if all trees can be harvested in this season
        for (HarvestDetailDto detailDto : harvestDto.getHarvestDetails()) {
            Tree tree = treeRepository.findById(detailDto.getTreeId())
                    .orElseThrow(() -> new RuntimeException("Tree not found"));

            Season treeSeason = getSeasonForTree(tree);
            if (!treeSeason.equals(harvestDto.getSeason())) {
                throw new TreeNotInSeasonException("Tree " + tree.getId() + " is not in the correct season for harvesting.");
            }
        }

        // Calculate the total quantity from harvest details
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

    @Override
    public List<HarvestDto> getHarvestsBySeason(String season) {
        return harvestRepository.findBySeason(Season.valueOf(season)).stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    private Season getSeasonForTree(Tree tree) {
        int month = tree.getPlantingDate().getMonthValue();
        if (month >= 3 && month <= 5) {
            return Season.SPRING;
        } else if (month >= 6 && month <= 8) {
            return Season.SUMMER;
        } else if (month >= 9 && month <= 11) {
            return Season.AUTUMN;
        } else {
            return Season.WINTER;
        }
    }
    
}
