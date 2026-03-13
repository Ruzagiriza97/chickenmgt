package com.example.chickenmgt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farms")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id", unique = true)
    private Location location;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chicken> chickens = new ArrayList<>();

    public Farm() {}

    public Farm(Long id, String name, User owner, Location location, List<Chicken> chickens) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.location = location;
        this.chickens = chickens != null ? chickens : new ArrayList<>();
    }

    public static FarmBuilder builder() {
        return new FarmBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public List<Chicken> getChickens() { return chickens; }
    public void setChickens(List<Chicken> chickens) { this.chickens = chickens; }

    public static class FarmBuilder {
        private Long id;
        private String name;
        private User owner;
        private Location location;
        private List<Chicken> chickens;

        FarmBuilder() {}

        public FarmBuilder id(Long id) { this.id = id; return this; }
        public FarmBuilder name(String name) { this.name = name; return this; }
        public FarmBuilder owner(User owner) { this.owner = owner; return this; }
        public FarmBuilder location(Location location) { this.location = location; return this; }
        public FarmBuilder chickens(List<Chicken> chickens) { this.chickens = chickens; return this; }

        public Farm build() {
            return new Farm(id, name, owner, location, chickens);
        }
    }
}
