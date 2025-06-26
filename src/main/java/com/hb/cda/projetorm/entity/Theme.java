package com.hb.cda.projetorm.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private Theme parent;

    @OneToMany(mappedBy = "parent")
    private List<Theme> children= new ArrayList<>();
    @ManyToMany(mappedBy = "themes")
    private List<Project> projects = new ArrayList<>();

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    public Theme getParent() {
        return parent;
    }

    public void setParent(Theme parent) {
        this.parent = parent;
    }

    public List<Theme> getChildren() {
        return children;
    }

    public void setChildren(List<Theme> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
