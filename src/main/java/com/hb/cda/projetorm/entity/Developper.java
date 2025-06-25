package com.hb.cda.projetorm.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
public class Developper  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "developper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DevMasteryTechno> devMasteryTechnos;
    @OneToMany(mappedBy = "developper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public List<DevMasteryTechno> getDevMasteryTechnos() {
        return devMasteryTechnos;
    }

    public void setDevMasteryTechnos(List<DevMasteryTechno> devMasteryTechnos) {
        this.devMasteryTechnos = devMasteryTechnos;
    }

    @Override
    public String toString() {
        return "Developper{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }
}
