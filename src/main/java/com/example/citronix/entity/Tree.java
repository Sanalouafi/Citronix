package com.example.citronix.entity;

import com.example.citronix.entity.enums.TreeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tree")

public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @Column(nullable = false)
    private LocalDate plantingDate;
    @Column(nullable = false)
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreeStatus status;
    @Column(nullable = false)
    private double productivity;

    public void updateCalculatedFields() {
        this.age = calculateAge();
        this.status = determineTreeStatus();
        this.productivity = calculateProductivity();
    }

    private int calculateAge() {
        return plantingDate != null ? Period.between(plantingDate, LocalDate.now()).getYears() : 0;
    }

    private TreeStatus determineTreeStatus() {
        if (age < 3) {
            return TreeStatus.YOUNG;
        } else if (age <= 10) {
            return TreeStatus.MATURE;
        } else if (age <= 20) {
            return TreeStatus.OLD;
        } else {
            return TreeStatus.NON_PRODUCTIVE;
        }
    }

    private double calculateProductivity() {
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;
        }
    }
}
