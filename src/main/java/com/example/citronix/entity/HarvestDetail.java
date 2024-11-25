package com.example.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "harvestDetail")

public class HarvestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @Column(nullable = false)
    private double quantity; 
}
