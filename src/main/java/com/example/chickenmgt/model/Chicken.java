package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "chickens")
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

    public Chicken() {}

    public Chicken(Long id, String breed, LocalDate hatchDate, Integer ageWeeks, Double weightKg, Farm farm) {
        this.id = id;
        this.breed = breed;
        this.hatchDate = hatchDate;
        this.ageWeeks = ageWeeks;
        this.weightKg = weightKg;
        this.farm = farm;
    }

    public static ChickenBuilder builder() {
        return new ChickenBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public LocalDate getHatchDate() { return hatchDate; }
    public void setHatchDate(LocalDate hatchDate) { this.hatchDate = hatchDate; }
    public Integer getAgeWeeks() { return ageWeeks; }
    public void setAgeWeeks(Integer ageWeeks) { this.ageWeeks = ageWeeks; }
    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }
    public Farm getFarm() { return farm; }
    public void setFarm(Farm farm) { this.farm = farm; }

    public static class ChickenBuilder {
        private Long id;
        private String breed;
        private LocalDate hatchDate;
        private Integer ageWeeks;
        private Double weightKg;
        private Farm farm;

        ChickenBuilder() {}

        public ChickenBuilder id(Long id) { this.id = id; return this; }
        public ChickenBuilder breed(String breed) { this.breed = breed; return this; }
        public ChickenBuilder hatchDate(LocalDate hatchDate) { this.hatchDate = hatchDate; return this; }
        public ChickenBuilder ageWeeks(Integer ageWeeks) { this.ageWeeks = ageWeeks; return this; }
        public ChickenBuilder weightKg(Double weightKg) { this.weightKg = weightKg; return this; }
        public ChickenBuilder farm(Farm farm) { this.farm = farm; return this; }

        public Chicken build() {
            return new Chicken(id, breed, hatchDate, ageWeeks, weightKg, farm);
        }
    }
}
