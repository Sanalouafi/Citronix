package com.example.citronix.request;

import com.example.citronix.entity.enums.Season;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HarvestRequest {

    @NotNull
    private Season season;

    @NotNull
    private LocalDate date;

    @NotNull
    private List<HarvestDetailRequest> harvestDetails;
}
