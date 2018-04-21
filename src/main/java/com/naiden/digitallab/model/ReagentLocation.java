package com.naiden.digitallab.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reagent_locations")
public class ReagentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCR")
    private String descr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReagentLocation reagentLocation = (ReagentLocation) o;
        return Objects.equals(descr, reagentLocation.descr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(descr);
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
