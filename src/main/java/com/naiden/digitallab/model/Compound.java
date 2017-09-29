package com.naiden.digitallab.model;

import javax.persistence.*;

@Entity
@Table(name = "compounds")
public class Compound {
    @Id
    @Column(name="COMPOUND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CID", unique = true)
    private String cid;

    @Column(name = "SMILES", unique = true)
    private String smiles;

    @Column(name = "FORMULA")
    private String formula;

    @Column(name = "SHORT_NAME", nullable = false, unique = true)
    private String shortName;

    @Column(name = "iupacName", nullable = false, unique = true)
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
