package com.tdmax.university.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @Column(name = "speciality_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "speciality_name")
    private String specialityName;

    @Column(name = "speciality_code")
    private String specialityCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafedra_id", nullable = false)
//    @JsonIgnore
    @JsonSerialize(as = Cafedra.class)
    private Cafedra cafedra;

    public Speciality() {}

    public Speciality(String specialityName, String specialityCode, Cafedra cafedra) {
        this.specialityName = specialityName;
        this.specialityCode = specialityCode;
        this.cafedra = cafedra;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    public Cafedra getCafedra() {
        return cafedra;
    }

    public void setCafedra(Cafedra cafedra) {
        this.cafedra = cafedra;
    }
}
