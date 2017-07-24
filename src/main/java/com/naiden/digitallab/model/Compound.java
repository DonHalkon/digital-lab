package com.naiden.digitallab.model;

import javax.persistence.*;

@Entity
@Table(name = "compounds")
public class Compound {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CAS")
    private String cas;

    @Column(name = "SMILES")
    private String smiles;

    @Column(name = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
