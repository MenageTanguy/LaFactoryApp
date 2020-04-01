package com.lafactory.app.service;

import com.lafactory.app.domain.Stagiaire;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Stagiaire}.
 */
public interface StagiaireService {

    /**
     * Save a stagiaire.
     *
     * @param stagiaire the entity to save.
     * @return the persisted entity.
     */
    Stagiaire save(Stagiaire stagiaire);

    /**
     * Get all the stagiaires.
     *
     * @return the list of entities.
     */
    List<Stagiaire> findAll();
    /**
     * Get all the StagiaireDTO where Ordinateur is {@code null}.
     *
     * @return the list of entities.
     */
    List<Stagiaire> findAllWhereOrdinateurIsNull();

    /**
     * Get the "id" stagiaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Stagiaire> findOne(Long id);

    /**
     * Delete the "id" stagiaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
