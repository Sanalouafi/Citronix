package com.example.citronix.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FieldRequest {
    @NotNull
    private Long farmId;
    @NotNull
    @DecimalMin("0.1")
    private Double area;
}
