package com.lafactory.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cursus.
 */
@Entity
@Table(name = "cursus")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cursus implements Serializable {

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

    @Column(name = "prerequis")
    private String prerequis;

    @Column(name = "objectifs")
    private String objectifs;

    @Column(name = "contenu")
    private String contenu;

    @OneToOne
    @JoinColumn(unique = true)
    private Salle salle;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cursus_stagiaires",
               joinColumns = @JoinColumn(name = "cursus_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "stagiaires_id", referencedColumnName = "id"))
    private Set<Stagiaire> stagiaires = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cursus_modules",
               joinColumns = @JoinColumn(name = "cursus_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "modules_id", referencedColumnName = "id"))
    private Set<Module> modules = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("cursuses")
    private Gestionnaire gestionnaire;

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

    public Cursus nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Cursus dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Cursus dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getPrerequis() {
        return prerequis;
    }

    public Cursus prerequis(String prerequis) {
        this.prerequis = prerequis;
        return this;
    }

    public void setPrerequis(String prerequis) {
        this.prerequis = prerequis;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public Cursus objectifs(String objectifs) {
        this.objectifs = objectifs;
        return this;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public String getContenu() {
        return contenu;
    }

    public Cursus contenu(String contenu) {
        this.contenu = contenu;
        return this;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Salle getSalle() {
        return salle;
    }

    public Cursus salle(Salle salle) {
        this.salle = salle;
        return this;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Set<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public Cursus stagiaires(Set<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
        return this;
    }

    public Cursus addStagiaires(Stagiaire stagiaire) {
        this.stagiaires.add(stagiaire);
        stagiaire.getCursuses().add(this);
        return this;
    }

    public Cursus removeStagiaires(Stagiaire stagiaire) {
        this.stagiaires.remove(stagiaire);
        stagiaire.getCursuses().remove(this);
        return this;
    }

    public void setStagiaires(Set<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Cursus modules(Set<Module> modules) {
        this.modules = modules;
        return this;
    }

    public Cursus addModules(Module module) {
        this.modules.add(module);
        module.getCursuses().add(this);
        return this;
    }

    public Cursus removeModules(Module module) {
        this.modules.remove(module);
        module.getCursuses().remove(this);
        return this;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Gestionnaire getGestionnaire() {
        return gestionnaire;
    }

    public Cursus gestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
        return this;
    }

    public void setGestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cursus)) {
            return false;
        }
        return id != null && id.equals(((Cursus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cursus{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", prerequis='" + getPrerequis() + "'" +
            ", objectifs='" + getObjectifs() + "'" +
            ", contenu='" + getContenu() + "'" +
            "}";
    }
}
