package com.hb.cda.projetorm.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Developper developper;
    @ManyToOne
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Developper getDevelopper() {
        return developper;
    }

    public void setDevelopper(Developper developper) {
        this.developper = developper;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "message='" + message + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
