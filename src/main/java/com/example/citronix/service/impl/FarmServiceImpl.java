package com.example.citronix.service.impl;

import com.example.citronix.dto.FarmDto;
import com.example.citronix.entity.Farm;
import com.example.citronix.exception.customException.FarmNotFoundException;
import com.example.citronix.mapper.FarmMapper;
import com.example.citronix.repository.FarmRepository;
import com.example.citronix.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public FarmDto createFarm(FarmDto farmDto) {
        Farm farm = farmMapper.toEntity(farmDto);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDto(savedFarm);
    }

    @Override
    public FarmDto updateFarm(Long id, FarmDto farmDto) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));
        existingFarm.setName(farmDto.getName());
        existingFarm.setLocation(farmDto.getLocation());
        existingFarm.setArea(farmDto.getArea());
        existingFarm.setCreationDate(farmDto.getCreationDate());
        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.toDto(updatedFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new FarmNotFoundException(id);
        }
        farmRepository.deleteById(id);
    }

    @Override
    public FarmDto getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));
        return farmMapper.toDto(farm);
    }

    @Override
    public List<FarmDto> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FarmDto> searchFarms(String location, Double minArea, Double maxArea, LocalDate creationDate) {
        List<Farm> filteredFarms = farmRepository.searchFarms(location, minArea, maxArea, creationDate);
        return filteredFarms.stream()
                .map(farmMapper::toDto)
                .collect(Collectors.toList());
    }
}
