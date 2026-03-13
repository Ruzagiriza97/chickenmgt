package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> districts = new ArrayList<>();

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

    public Province() {}

    public Province(Long id, String name, String code, List<District> districts, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.districts = districts != null ? districts : new ArrayList<>();
        this.locations = locations != null ? locations : new ArrayList<>();
    }

    public static ProvinceBuilder builder() {
        return new ProvinceBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public List<District> getDistricts() { return districts; }
    public void setDistricts(List<District> districts) { this.districts = districts; }
    public List<Location> getLocations() { return locations; }
    public void setLocations(List<Location> locations) { this.locations = locations; }

    public static class ProvinceBuilder {
        private Long id;
        private String name;
        private String code;
        private List<District> districts;
        private List<Location> locations;

        ProvinceBuilder() {}

        public ProvinceBuilder id(Long id) { this.id = id; return this; }
        public ProvinceBuilder name(String name) { this.name = name; return this; }
        public ProvinceBuilder code(String code) { this.code = code; return this; }
        public ProvinceBuilder districts(List<District> districts) { this.districts = districts; return this; }
        public ProvinceBuilder locations(List<Location> locations) { this.locations = locations; return this; }

        public Province build() {
            return new Province(id, name, code, districts, locations);
        }
    }
}
