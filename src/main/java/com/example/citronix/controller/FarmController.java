package com.example.citronix.controller;

import com.example.citronix.dto.FarmDto;
import com.example.citronix.request.FarmRequest;
import com.example.citronix.service.FarmService;
import com.example.citronix.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;
    private final FarmMapper farmMapper;

    @PostMapping
    public ResponseEntity<FarmDto> createFarm(@Valid @RequestBody FarmRequest farmRequest) {
        FarmDto farmDto = farmMapper.toDto(farmMapper.toEntity(farmRequest));
        FarmDto createdFarm = farmService.createFarm(farmDto);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
        FarmDto farmDto = farmService.getFarmById(id);
        return ResponseEntity.ok(farmDto);
    }

    @GetMapping
    public ResponseEntity<List<FarmDto>> getAllFarms() {
        List<FarmDto> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDto> updateFarm(@PathVariable Long id, @Valid @RequestBody FarmRequest farmRequest) {
        FarmDto farmDto = farmMapper.toDto(farmMapper.toEntity(farmRequest));
        FarmDto updatedFarm = farmService.updateFarm(id, farmDto);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmDto>> searchFarms(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minArea,
            @RequestParam(required = false) Double maxArea,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate) {

        List<FarmDto> farms = farmService.searchFarms(location, minArea, maxArea, creationDate);
        return ResponseEntity.ok(farms);
    }
}
