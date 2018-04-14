package com.naiden.digitallab.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "compounds")
public class Compound {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CID", unique = true)
    private String cid;

    @Column(name = "JSME_FILE", unique = true)
    private String jmeFile;

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

    public String getJmeFile() {
        return jmeFile;
    }

    public void setJmeFile(String jmeFile) {
        this.jmeFile = jmeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compound compound = (Compound) o;
        return Objects.equals(cid, compound.cid) &&
                Objects.equals(jmeFile, compound.jmeFile) &&
                Objects.equals(formula, compound.formula) &&
                Objects.equals(shortName, compound.shortName) &&
                Objects.equals(iupacName, compound.iupacName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, jmeFile, formula, shortName, iupacName);
    }
}
