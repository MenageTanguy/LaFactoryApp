package com.lafactory.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Salle.
 */
@Entity
@Table(name = "salle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "cout")
    private Float cout;

    @Column(name = "capacite_max")
    private Integer capaciteMax;

    @OneToOne(mappedBy = "salle")
    @JsonIgnore
    private Projecteur projecteur;

    @OneToOne(mappedBy = "salle")
    @JsonIgnore
    private Cursus cursus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Salle code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getCout() {
        return cout;
    }

    public Salle cout(Float cout) {
        this.cout = cout;
        return this;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }

    public Integer getCapaciteMax() {
        return capaciteMax;
    }

    public Salle capaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
        return this;
    }

    public void setCapaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Projecteur getProjecteur() {
        return projecteur;
    }

    public Salle projecteur(Projecteur projecteur) {
        this.projecteur = projecteur;
        return this;
    }

    public void setProjecteur(Projecteur projecteur) {
        this.projecteur = projecteur;
    }

    public Cursus getCursus() {
        return cursus;
    }

    public Salle cursus(Cursus cursus) {
        this.cursus = cursus;
        return this;
    }

    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salle)) {
            return false;
        }
        return id != null && id.equals(((Salle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Salle{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", cout=" + getCout() +
            ", capaciteMax=" + getCapaciteMax() +
            "}";
    }
}
