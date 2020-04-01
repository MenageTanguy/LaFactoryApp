package com.lafactory.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Ordinateur.
 */
@Entity
@Table(name = "ordinateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ordinateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "cout")
    private Float cout;

    @Column(name = "processeur")
    private String processeur;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "dd")
    private Integer dd;

    @Column(name = "date_achat")
    private LocalDate dateAchat;

    @Column(name = "stock")
    private Integer stock;

    @OneToOne
    @JoinColumn(unique = true)
    private Stagiaire stagiaire;

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

    public Ordinateur code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getCout() {
        return cout;
    }

    public Ordinateur cout(Float cout) {
        this.cout = cout;
        return this;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }

    public String getProcesseur() {
        return processeur;
    }

    public Ordinateur processeur(String processeur) {
        this.processeur = processeur;
        return this;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public Integer getRam() {
        return ram;
    }

    public Ordinateur ram(Integer ram) {
        this.ram = ram;
        return this;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getDd() {
        return dd;
    }

    public Ordinateur dd(Integer dd) {
        this.dd = dd;
        return this;
    }

    public void setDd(Integer dd) {
        this.dd = dd;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public Ordinateur dateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
        return this;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Integer getStock() {
        return stock;
    }

    public Ordinateur stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public Ordinateur stagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
        return this;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ordinateur)) {
            return false;
        }
        return id != null && id.equals(((Ordinateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ordinateur{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", cout=" + getCout() +
            ", processeur='" + getProcesseur() + "'" +
            ", ram=" + getRam() +
            ", dd=" + getDd() +
            ", dateAchat='" + getDateAchat() + "'" +
            ", stock=" + getStock() +
            "}";
    }
}
