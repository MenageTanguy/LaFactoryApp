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
 * A Formateur.
 */
@Entity
@Table(name = "formateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Formateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "coordonnees")
    private String coordonnees;

    @Column(name = "numero_rue")
    private Integer numeroRue;

    @Column(name = "rue")
    private String rue;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "formateur_matieres_debutant",
               joinColumns = @JoinColumn(name = "formateur_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matieres_debutant_id", referencedColumnName = "id"))
    private Set<Matiere> matieresDebutants = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "formateur_matieres_intermedaire",
               joinColumns = @JoinColumn(name = "formateur_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matieres_intermedaire_id", referencedColumnName = "id"))
    private Set<Matiere> matieresIntermedaires = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "formateur_matieres_avance",
               joinColumns = @JoinColumn(name = "formateur_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matieres_avance_id", referencedColumnName = "id"))
    private Set<Matiere> matieresAvances = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "formateur_matieres_confirme",
               joinColumns = @JoinColumn(name = "formateur_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matieres_confirme_id", referencedColumnName = "id"))
    private Set<Matiere> matieresConfirmes = new HashSet<>();

    @ManyToMany(mappedBy = "formateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Matiere> matieres = new HashSet<>();

    @ManyToMany(mappedBy = "formateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Module> modules = new HashSet<>();

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

    public Formateur nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Formateur prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public Formateur coordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
        return this;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public Formateur numeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
        return this;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getRue() {
        return rue;
    }

    public Formateur rue(String rue) {
        this.rue = rue;
        return this;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Formateur codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public Formateur ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Set<Matiere> getMatieresDebutants() {
        return matieresDebutants;
    }

    public Formateur matieresDebutants(Set<Matiere> matieres) {
        this.matieresDebutants = matieres;
        return this;
    }

    public Formateur addMatieresDebutant(Matiere matiere) {
        this.matieresDebutants.add(matiere);
        matiere.getDebutantMatieres().add(this);
        return this;
    }

    public Formateur removeMatieresDebutant(Matiere matiere) {
        this.matieresDebutants.remove(matiere);
        matiere.getDebutantMatieres().remove(this);
        return this;
    }

    public void setMatieresDebutants(Set<Matiere> matieres) {
        this.matieresDebutants = matieres;
    }

    public Set<Matiere> getMatieresIntermedaires() {
        return matieresIntermedaires;
    }

    public Formateur matieresIntermedaires(Set<Matiere> matieres) {
        this.matieresIntermedaires = matieres;
        return this;
    }

    public Formateur addMatieresIntermedaire(Matiere matiere) {
        this.matieresIntermedaires.add(matiere);
        matiere.getIntermediaireMatieres().add(this);
        return this;
    }

    public Formateur removeMatieresIntermedaire(Matiere matiere) {
        this.matieresIntermedaires.remove(matiere);
        matiere.getIntermediaireMatieres().remove(this);
        return this;
    }

    public void setMatieresIntermedaires(Set<Matiere> matieres) {
        this.matieresIntermedaires = matieres;
    }

    public Set<Matiere> getMatieresAvances() {
        return matieresAvances;
    }

    public Formateur matieresAvances(Set<Matiere> matieres) {
        this.matieresAvances = matieres;
        return this;
    }

    public Formateur addMatieresAvance(Matiere matiere) {
        this.matieresAvances.add(matiere);
        matiere.getAvanceMatieres().add(this);
        return this;
    }

    public Formateur removeMatieresAvance(Matiere matiere) {
        this.matieresAvances.remove(matiere);
        matiere.getAvanceMatieres().remove(this);
        return this;
    }

    public void setMatieresAvances(Set<Matiere> matieres) {
        this.matieresAvances = matieres;
    }

    public Set<Matiere> getMatieresConfirmes() {
        return matieresConfirmes;
    }

    public Formateur matieresConfirmes(Set<Matiere> matieres) {
        this.matieresConfirmes = matieres;
        return this;
    }

    public Formateur addMatieresConfirme(Matiere matiere) {
        this.matieresConfirmes.add(matiere);
        matiere.getConfirmeMatieres().add(this);
        return this;
    }

    public Formateur removeMatieresConfirme(Matiere matiere) {
        this.matieresConfirmes.remove(matiere);
        matiere.getConfirmeMatieres().remove(this);
        return this;
    }

    public void setMatieresConfirmes(Set<Matiere> matieres) {
        this.matieresConfirmes = matieres;
    }

    public Set<Matiere> getMatieres() {
        return matieres;
    }

    public Formateur matieres(Set<Matiere> matieres) {
        this.matieres = matieres;
        return this;
    }

    public Formateur addMatieres(Matiere matiere) {
        this.matieres.add(matiere);
        matiere.getFormateurs().add(this);
        return this;
    }

    public Formateur removeMatieres(Matiere matiere) {
        this.matieres.remove(matiere);
        matiere.getFormateurs().remove(this);
        return this;
    }

    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public Formateur modules(Set<Module> modules) {
        this.modules = modules;
        return this;
    }

    public Formateur addModules(Module module) {
        this.modules.add(module);
        module.getFormateurs().add(this);
        return this;
    }

    public Formateur removeModules(Module module) {
        this.modules.remove(module);
        module.getFormateurs().remove(this);
        return this;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formateur)) {
            return false;
        }
        return id != null && id.equals(((Formateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Formateur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", coordonnees='" + getCoordonnees() + "'" +
            ", numeroRue=" + getNumeroRue() +
            ", rue='" + getRue() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", ville='" + getVille() + "'" +
            "}";
    }
}
