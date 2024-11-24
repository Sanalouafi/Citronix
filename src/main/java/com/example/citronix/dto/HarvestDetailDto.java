package com.example.citronix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDetailDto {

    private Long id;
    private Long treeId;
    private double quantity; // Quantity harvested from the specific tree in kilograms
}
