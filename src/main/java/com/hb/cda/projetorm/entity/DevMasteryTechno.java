package com.hb.cda.projetorm.entity;

import jakarta.persistence.*;

@Entity
public class DevMasteryTechno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Mastery mastery;
    @ManyToOne
    private Techno techno;
    @ManyToOne
    private Developper developper;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mastery getMastery() {
        return mastery;
    }

    public void setMastery(Mastery mastery) {
        this.mastery = mastery;
    }

    public Techno getTechno() {
        return techno;
    }

    public void setTechno(Techno techno) {
        this.techno = techno;
    }

    public Developper getDevelopper() {
        return developper;
    }

    public void setDevelopper(Developper developper) {
        this.developper = developper;
    }
}
