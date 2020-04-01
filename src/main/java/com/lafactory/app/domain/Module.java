package com.lafactory.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Module.
 */
@Entity
@Table(name = "module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "module_matieres",
               joinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matieres_id", referencedColumnName = "id"))
    private Set<Matiere> matieres = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "module_formateurs",
               joinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "formateurs_id", referencedColumnName = "id"))
    private Set<Formateur> formateurs = new HashSet<>();

    @ManyToMany(mappedBy = "modules")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Cursus> cursuses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Module nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Module dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Module dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Set<Matiere> getMatieres() {
        return matieres;
    }

    public Module matieres(Set<Matiere> matieres) {
        this.matieres = matieres;
        return this;
    }

    public Module addMatieres(Matiere matiere) {
        this.matieres.add(matiere);
        matiere.getModules().add(this);
        return this;
    }

    public Module removeMatieres(Matiere matiere) {
        this.matieres.remove(matiere);
        matiere.getModules().remove(this);
        return this;
    }

    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Set<Formateur> getFormateurs() {
        return formateurs;
    }

    public Module formateurs(Set<Formateur> formateurs) {
        this.formateurs = formateurs;
        return this;
    }

    public Module addFormateurs(Formateur formateur) {
        this.formateurs.add(formateur);
        formateur.getModules().add(this);
        return this;
    }

    public Module removeFormateurs(Formateur formateur) {
        this.formateurs.remove(formateur);
        formateur.getModules().remove(this);
        return this;
    }

    public void setFormateurs(Set<Formateur> formateurs) {
        this.formateurs = formateurs;
    }

    public Set<Cursus> getCursuses() {
        return cursuses;
    }

    public Module cursuses(Set<Cursus> cursuses) {
        this.cursuses = cursuses;
        return this;
    }

    public Module addCursus(Cursus cursus) {
        this.cursuses.add(cursus);
        cursus.getModules().add(this);
        return this;
    }

    public Module removeCursus(Cursus cursus) {
        this.cursuses.remove(cursus);
        cursus.getModules().remove(this);
        return this;
    }

    public void setCursuses(Set<Cursus> cursuses) {
        this.cursuses = cursuses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Module)) {
            return false;
        }
        return id != null && id.equals(((Module) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Module{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            "}";
    }
}
