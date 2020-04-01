package com.lafactory.app.service;

import com.lafactory.app.domain.Gestionnaire;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Gestionnaire}.
 */
public interface GestionnaireService {

    /**
     * Save a gestionnaire.
     *
     * @param gestionnaire the entity to save.
     * @return the persisted entity.
     */
    Gestionnaire save(Gestionnaire gestionnaire);

    /**
     * Get all the gestionnaires.
     *
     * @return the list of entities.
     */
    List<Gestionnaire> findAll();

    /**
     * Get the "id" gestionnaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Gestionnaire> findOne(Long id);

    /**
     * Delete the "id" gestionnaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
