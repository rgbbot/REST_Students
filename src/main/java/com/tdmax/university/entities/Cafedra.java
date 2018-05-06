package com.tdmax.university.entities;

import javax.persistence.*;

@Entity
@Table(name = "cafedra")
public class Cafedra {

    @Id
    @Column(name = "cafedra_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "cafedra_name")
    private String cafedraName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCafedraName() {
        return cafedraName;
    }

    public void setCafedraName(String cafedraName) {
        this.cafedraName = cafedraName;
    }

    public Cafedra() {}

    public Cafedra(String cafedraName) {
        this.cafedraName = cafedraName;
    }
}
