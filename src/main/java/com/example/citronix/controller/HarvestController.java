package com.example.citronix.controller;

import com.example.citronix.dto.HarvestDto;
import com.example.citronix.request.HarvestRequest;
import com.example.citronix.service.HarvestService;
import com.example.citronix.mapper.HarvestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    @PostMapping
    public ResponseEntity<HarvestDto> createHarvest(@Valid @RequestBody HarvestRequest harvestRequest) {
        HarvestDto harvestDto = harvestMapper.toDto(harvestMapper.toEntity(harvestRequest));
        HarvestDto createdHarvest = harvestService.createHarvest(harvestDto);
        return new ResponseEntity<>(createdHarvest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDto> getHarvestById(@PathVariable Long id) {
        HarvestDto harvestDto = harvestService.getHarvestById(id);
        return ResponseEntity.ok(harvestDto);
    }

    @GetMapping
    public ResponseEntity<List<HarvestDto>> getAllHarvests() {
        List<HarvestDto> harvests = harvestService.getAllHarvests();
        return ResponseEntity.ok(harvests);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return ResponseEntity.noContent().build();
    }
}
