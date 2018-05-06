package com.tdmax.university.entities;

import javax.persistence.*;

@Entity
@Table(name = "sdiploma")
public class Diploma {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "diploma_name")
    private String diplomaName;

    public Diploma(Long id, String diplomaName) {
        this.id = id;
        this.diplomaName = diplomaName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiplomaName() {
        return diplomaName;
    }

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }
}
