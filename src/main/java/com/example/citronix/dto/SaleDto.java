package com.example.citronix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    private Long id;
    private Long harvestId;
    private String clientName;
    private LocalDate date;
    private double unitPrice;
    private double quantity;
    private double revenue;
}
