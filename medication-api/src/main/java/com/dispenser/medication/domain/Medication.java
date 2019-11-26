package com.dispenser.medication.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Medication.
 */
@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1)
    @Column(name = "patient_id", unique = true)
    private Integer patient_id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "dose_date", nullable = false)
    private LocalDate dose_date;

    @Column(name = "nihurl")
    private String nihurl;

    @Column(name = "contraindications")
    private String contraindications;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public Medication patient_id(Integer patient_id) {
        this.patient_id = patient_id;
        return this;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public Medication name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDose_date() {
        return dose_date;
    }

    public Medication dose_date(LocalDate dose_date) {
        this.dose_date = dose_date;
        return this;
    }

    public void setDose_date(LocalDate dose_date) {
        this.dose_date = dose_date;
    }

    public String getNihurl() {
        return nihurl;
    }

    public Medication nihurl(String nihurl) {
        this.nihurl = nihurl;
        return this;
    }

    public void setNihurl(String nihurl) {
        this.nihurl = nihurl;
    }

    public String getContraindications() {
        return contraindications;
    }

    public Medication contraindications(String contraindications) {
        this.contraindications = contraindications;
        return this;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medication)) {
            return false;
        }
        return id != null && id.equals(((Medication) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Medication{" +
            "id=" + getId() +
            ", patient_id=" + getPatient_id() +
            ", name='" + getName() + "'" +
            ", dose_date='" + getDose_date() + "'" +
            ", nihurl='" + getNihurl() + "'" +
            ", contraindications='" + getContraindications() + "'" +
            "}";
    }
}
