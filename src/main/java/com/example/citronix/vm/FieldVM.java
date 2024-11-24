package com.example.citronix.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldVM {

    private Long id;
    private Long farmId;
    private Double area;
    private Double maxAllowedArea;
    private int totalFieldsInFarm;
}
