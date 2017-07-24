package com.naiden.digitallab.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reagents")
public class Reagent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REAGENT_ID")
    private int id;

    @Column(name="COMPOUND_ID")
    private int compoundId;

    @Column(name="LOCATION_ID")
    private int locationId;

    @Column(name="RECEIPT_DATE")
    private Date receiptDate;

    @Column(name="STORAGE_LIFE")
    private Date storageLife;

    @Column(name = "COMMENTS")
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompoundId() {
        return compoundId;
    }

    public void setCompoundId(int compoundId) {
        this.compoundId = compoundId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getStorageLife() {
        return storageLife;
    }

    public void setStorageLife(Date storageLife) {
        this.storageLife = storageLife;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
