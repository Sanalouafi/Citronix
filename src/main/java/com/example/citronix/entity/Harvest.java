package com.example.citronix.entity;

import com.example.citronix.entity.enums.HarvestStatus;
import com.example.citronix.entity.enums.Season;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "harvest")

public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;

    @Column(nullable = false)
    private LocalDate date; // Date of the harvest

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HarvestStatus status;

    @Column(nullable = false)
    private double totalQuantity; // Total quantity harvested in kilograms

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetails;
}
