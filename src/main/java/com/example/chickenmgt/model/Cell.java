package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cells")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;

    @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Village> villages = new ArrayList<>();

    public Cell() {}

    public Cell(Long id, String name, String code, Sector sector, List<Village> villages) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sector = sector;
        this.villages = villages != null ? villages : new ArrayList<>();
    }

    public static CellBuilder builder() {
        return new CellBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }
    public List<Village> getVillages() { return villages; }
    public void setVillages(List<Village> villages) { this.villages = villages; }

    public static class CellBuilder {
        private Long id;
        private String name;
        private String code;
        private Sector sector;
        private List<Village> villages;

        CellBuilder() {}

        public CellBuilder id(Long id) { this.id = id; return this; }
        public CellBuilder name(String name) { this.name = name; return this; }
        public CellBuilder code(String code) { this.code = code; return this; }
        public CellBuilder sector(Sector sector) { this.sector = sector; return this; }
        public CellBuilder villages(List<Village> villages) { this.villages = villages; return this; }

        public Cell build() {
            return new Cell(id, name, code, sector, villages);
        }
    }
}
