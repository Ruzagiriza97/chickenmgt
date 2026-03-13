package com.example.chickenmgt.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "chickens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chicken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String breed;

    @Column(name = "hatch_date")
    private LocalDate hatchDate;

    @Column(name = "age_weeks")
    private Integer ageWeeks;

    @Column(name = "weight_kg")
    private Double weightKg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}
