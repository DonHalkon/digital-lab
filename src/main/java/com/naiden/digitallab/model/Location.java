package com.naiden.digitallab.model;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name="LOCATION_ID")
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

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
