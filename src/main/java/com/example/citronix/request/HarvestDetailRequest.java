package com.example.citronix.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HarvestDetailRequest {

    @NotNull
    private Long treeId;

    @Positive
    private double quantity;
}
