package com.example.citronix.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FarmRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @DecimalMin("0.1")
    private Double area;

    @NotNull
    @PastOrPresent
    private LocalDate creationDate;
}
