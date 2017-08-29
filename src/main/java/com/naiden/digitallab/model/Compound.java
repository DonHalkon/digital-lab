package com.naiden.digitallab.model;

import javax.persistence.*;

@Entity
@Table(name = "compounds")
public class Compound {
    @Id
    @Column(name="COMPOUND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CID")
    private String cid;

    @Column(name = "SMILES")
    private String smiles;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "iupacName")
    private String iupacName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIupacName() {
        return iupacName;
    }

    public void setIupacName(String iupacName) {
        this.iupacName = iupacName;
    }
}
