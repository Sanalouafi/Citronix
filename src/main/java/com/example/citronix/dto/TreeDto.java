package com.example.citronix.dto;

import com.example.citronix.entity.enums.TreeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDto {

    private Long id;
    private Long fieldId;
    private LocalDate plantingDate;
    private int age;               // Calculated age in years
    private double productivity;   // Annual productivity based on age
    private TreeStatus status;
}
