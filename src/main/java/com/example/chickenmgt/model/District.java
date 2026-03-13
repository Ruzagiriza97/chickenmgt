package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sector> sectors = new ArrayList<>();

    public District() {}

    public District(Long id, String name, String code, Province province, List<Sector> sectors) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.province = province;
        this.sectors = sectors != null ? sectors : new ArrayList<>();
    }

    public static DistrictBuilder builder() {
        return new DistrictBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Province getProvince() { return province; }
    public void setProvince(Province province) { this.province = province; }
    public List<Sector> getSectors() { return sectors; }
    public void setSectors(List<Sector> sectors) { this.sectors = sectors; }

    public static class DistrictBuilder {
        private Long id;
        private String name;
        private String code;
        private Province province;
        private List<Sector> sectors;

        DistrictBuilder() {}

        public DistrictBuilder id(Long id) { this.id = id; return this; }
        public DistrictBuilder name(String name) { this.name = name; return this; }
        public DistrictBuilder code(String code) { this.code = code; return this; }
        public DistrictBuilder province(Province province) { this.province = province; return this; }
        public DistrictBuilder sectors(List<Sector> sectors) { this.sectors = sectors; return this; }

        public District build() {
            return new District(id, name, code, province, sectors);
        }
    }
}
