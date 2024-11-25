package com.example.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double revenue;
}
