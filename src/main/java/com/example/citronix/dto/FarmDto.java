package com.example.citronix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {

    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate creationDate;
}
