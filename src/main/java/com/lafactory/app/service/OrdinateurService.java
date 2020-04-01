package com.lafactory.app.service;

import com.lafactory.app.domain.Ordinateur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Ordinateur}.
 */
public interface OrdinateurService {

    /**
     * Save a ordinateur.
     *
     * @param ordinateur the entity to save.
     * @return the persisted entity.
     */
    Ordinateur save(Ordinateur ordinateur);

    /**
     * Get all the ordinateurs.
     *
     * @return the list of entities.
     */
    List<Ordinateur> findAll();

    /**
     * Get the "id" ordinateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Ordinateur> findOne(Long id);

    /**
     * Delete the "id" ordinateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
