package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cell> cells = new ArrayList<>();

    public Sector() {}

    public Sector(Long id, String name, String code, District district, List<Cell> cells) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.district = district;
        this.cells = cells != null ? cells : new ArrayList<>();
    }

    public static SectorBuilder builder() {
        return new SectorBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public District getDistrict() { return district; }
    public void setDistrict(District district) { this.district = district; }
    public List<Cell> getCells() { return cells; }
    public void setCells(List<Cell> cells) { this.cells = cells; }

    public static class SectorBuilder {
        private Long id;
        private String name;
        private String code;
        private District district;
        private List<Cell> cells;

        SectorBuilder() {}

        public SectorBuilder id(Long id) { this.id = id; return this; }
        public SectorBuilder name(String name) { this.name = name; return this; }
        public SectorBuilder code(String code) { this.code = code; return this; }
        public SectorBuilder district(District district) { this.district = district; return this; }
        public SectorBuilder cells(List<Cell> cells) { this.cells = cells; return this; }

        public Sector build() {
            return new Sector(id, name, code, district, cells);
        }
    }
}
