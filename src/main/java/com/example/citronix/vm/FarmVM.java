package com.example.citronix.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmVM {

    private Long id;
    private String name;
    private String location;
    private double area;
    private double totalRevenue;
}
