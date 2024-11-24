package com.example.citronix.dto;

import com.example.citronix.entity.enums.HarvestStatus;
import com.example.citronix.entity.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDto {

    private Long id;
    private Season season;
    private LocalDate date;
    private HarvestStatus status;
    private double totalQuantity;
    private List<HarvestDetailDto> harvestDetails; // List of HarvestDetailDto
}
