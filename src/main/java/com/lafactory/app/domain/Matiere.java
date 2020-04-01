package com.lafactory.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Matiere.
 */
@Entity
@Table(name = "matiere")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Matiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "duree")
    private Integer duree;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "matiere_formateurs",
               joinColumns = @JoinColumn(name = "matiere_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "formateurs_id", referencedColumnName = "id"))
    private Set<Formateur> formateurs = new HashSet<>();

    @ManyToMany(mappedBy = "matieres")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Module> modules = new HashSet<>();

    @ManyToMany(mappedBy = "matieresDebutants")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Formateur> debutantMatieres = new HashSet<>();

    @ManyToMany(mappedBy = "matieresIntermedaires")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Formateur> intermediaireMatieres = new HashSet<>();

    @ManyToMany(mappedBy = "matieresAvances")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Formateur> avanceMatieres = new HashSet<>();

    @ManyToMany(mappedBy = "matieresConfirmes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Formateur> confirmeMatieres = new HashSet<>();

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

    public Matiere nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getDuree() {
        return duree;
    }

    public Matiere duree(Integer duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Set<Formateur> getFormateurs() {
        return formateurs;
    }

    public Matiere formateurs(Set<Formateur> formateurs) {
        this.formateurs = formateurs;
        return this;
    }

    public Matiere addFormateurs(Formateur formateur) {
        this.formateurs.add(formateur);
        formateur.getMatieres().add(this);
        return this;
    }

    public Matiere removeFormateurs(Formateur formateur) {
        this.formateurs.remove(formateur);
        formateur.getMatieres().remove(this);
        return this;
    }

    public void setFormateurs(Set<Formateur> formateurs) {
        this.formateurs = formateurs;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Matiere modules(Set<Module> modules) {
        this.modules = modules;
        return this;
    }

    public Matiere addModules(Module module) {
        this.modules.add(module);
        module.getMatieres().add(this);
        return this;
    }

    public Matiere removeModules(Module module) {
        this.modules.remove(module);
        module.getMatieres().remove(this);
        return this;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Formateur> getDebutantMatieres() {
        return debutantMatieres;
    }

    public Matiere debutantMatieres(Set<Formateur> formateurs) {
        this.debutantMatieres = formateurs;
        return this;
    }

    public Matiere addDebutantMatieres(Formateur formateur) {
        this.debutantMatieres.add(formateur);
        formateur.getMatieresDebutants().add(this);
        return this;
    }

    public Matiere removeDebutantMatieres(Formateur formateur) {
        this.debutantMatieres.remove(formateur);
        formateur.getMatieresDebutants().remove(this);
        return this;
    }

    public void setDebutantMatieres(Set<Formateur> formateurs) {
        this.debutantMatieres = formateurs;
    }

    public Set<Formateur> getIntermediaireMatieres() {
        return intermediaireMatieres;
    }

    public Matiere intermediaireMatieres(Set<Formateur> formateurs) {
        this.intermediaireMatieres = formateurs;
        return this;
    }

    public Matiere addIntermediaireMatieres(Formateur formateur) {
        this.intermediaireMatieres.add(formateur);
        formateur.getMatieresIntermedaires().add(this);
        return this;
    }

    public Matiere removeIntermediaireMatieres(Formateur formateur) {
        this.intermediaireMatieres.remove(formateur);
        formateur.getMatieresIntermedaires().remove(this);
        return this;
    }

    public void setIntermediaireMatieres(Set<Formateur> formateurs) {
        this.intermediaireMatieres = formateurs;
    }

    public Set<Formateur> getAvanceMatieres() {
        return avanceMatieres;
    }

    public Matiere avanceMatieres(Set<Formateur> formateurs) {
        this.avanceMatieres = formateurs;
        return this;
    }

    public Matiere addAvanceMatieres(Formateur formateur) {
        this.avanceMatieres.add(formateur);
        formateur.getMatieresAvances().add(this);
        return this;
    }

    public Matiere removeAvanceMatieres(Formateur formateur) {
        this.avanceMatieres.remove(formateur);
        formateur.getMatieresAvances().remove(this);
        return this;
    }

    public void setAvanceMatieres(Set<Formateur> formateurs) {
        this.avanceMatieres = formateurs;
    }

    public Set<Formateur> getConfirmeMatieres() {
        return confirmeMatieres;
    }

    public Matiere confirmeMatieres(Set<Formateur> formateurs) {
        this.confirmeMatieres = formateurs;
        return this;
    }

    public Matiere addConfirmeMatieres(Formateur formateur) {
        this.confirmeMatieres.add(formateur);
        formateur.getMatieresConfirmes().add(this);
        return this;
    }

    public Matiere removeConfirmeMatieres(Formateur formateur) {
        this.confirmeMatieres.remove(formateur);
        formateur.getMatieresConfirmes().remove(this);
        return this;
    }

    public void setConfirmeMatieres(Set<Formateur> formateurs) {
        this.confirmeMatieres = formateurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matiere)) {
            return false;
        }
        return id != null && id.equals(((Matiere) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Matiere{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", duree=" + getDuree() +
            "}";
    }
}
