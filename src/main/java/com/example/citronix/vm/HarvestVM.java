package com.example.citronix.vm;

import com.example.citronix.entity.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestVM {

    private Long id;
    private Season season;
    private LocalDate date;
    private double totalQuantity;
    private String status;
}
