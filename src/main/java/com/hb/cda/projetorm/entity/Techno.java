package com.hb.cda.projetorm.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Techno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "techno")
    private Set<DevMasteryTechno> devMasteryTechnos= new HashSet<>();;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DevMasteryTechno> getDevMasteryTechnos() {
        return devMasteryTechnos;
    }

    public void setDevMasteryTechnos(Set<DevMasteryTechno> devMasteryTechnos) {
        this.devMasteryTechnos = devMasteryTechnos;
    }

    @Override
    public String toString() {
        return "Techno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
