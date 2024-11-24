package com.example.citronix.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeRequest {

    @NotNull
    private Long fieldId;

    @NotNull
    @PastOrPresent  // Planting date cannot be in the future
    private LocalDate plantingDate;
}
