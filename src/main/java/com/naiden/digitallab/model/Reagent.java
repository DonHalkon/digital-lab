package com.naiden.digitallab.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reagents")
public class Reagent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPOUND_ID")
    private Compound compound;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private ReagentLocation reagentLocation;

    @Column(name="RECEIPT_DATE")
    private Date receiptDate;

    @Column(name="STORAGE_LIFE")
    private String storageLife;

    @Column(name = "COMMENTS")
    private String comments;

    @Enumerated(EnumType.ORDINAL)
    private MeasurementUnits measurementUnits;

    @Column(name = "AMOUNT")
    private Double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

    public ReagentLocation getReagentLocation() {
        return reagentLocation;
    }

    public void setReagentLocation(ReagentLocation reagentLocation) {
        this.reagentLocation = reagentLocation;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getStorageLife() {
        return storageLife;
    }

    public void setStorageLife(String storageLife) {
        this.storageLife = storageLife;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MeasurementUnits getMeasurementUnits() {
        return measurementUnits;
    }

    public void setMeasurementUnits(MeasurementUnits measurementUnits) {
        this.measurementUnits = measurementUnits;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reagent reagent = (Reagent) o;
        return Objects.equals(compound, reagent.compound) &&
                Objects.equals(reagentLocation, reagent.reagentLocation) &&
                Objects.equals(receiptDate, reagent.receiptDate) &&
                Objects.equals(storageLife, reagent.storageLife) &&
                Objects.equals(comments, reagent.comments) &&
                measurementUnits == reagent.measurementUnits &&
                Objects.equals(amount, reagent.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(compound, reagentLocation, receiptDate, storageLife, comments, measurementUnits, amount);
    }
}
