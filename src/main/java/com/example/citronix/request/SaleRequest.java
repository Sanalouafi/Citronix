package com.example.citronix.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleRequest {

    private Long harvestId;
    private String clientName;
    private LocalDate date;
    private double unitPrice;
    private double quantity;
}
