package com.example.chickenmgt.model;

import jakarta.persistence.*;


@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToOne(mappedBy = "location")
    private Farm farm;

    public Location() {}

    public Location(Long id, String address, Province province, Farm farm) {
        this.id = id;
        this.address = address;
        this.province = province;
        this.farm = farm;
    }

    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Province getProvince() { return province; }
    public void setProvince(Province province) { this.province = province; }
    public Farm getFarm() { return farm; }
    public void setFarm(Farm farm) { this.farm = farm; }

    public static class LocationBuilder {
        private Long id;
        private String address;
        private Province province;
        private Farm farm;

        LocationBuilder() {}

        public LocationBuilder id(Long id) { this.id = id; return this; }
        public LocationBuilder address(String address) { this.address = address; return this; }
        public LocationBuilder province(Province province) { this.province = province; return this; }
        public LocationBuilder farm(Farm farm) { this.farm = farm; return this; }

        public Location build() {
            return new Location(id, address, province, farm);
        }
    }
}
