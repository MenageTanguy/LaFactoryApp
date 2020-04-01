package com.lafactory.app.service;

import com.lafactory.app.domain.Formateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Formateur}.
 */
public interface FormateurService {

    /**
     * Save a formateur.
     *
     * @param formateur the entity to save.
     * @return the persisted entity.
     */
    Formateur save(Formateur formateur);

    /**
     * Get all the formateurs.
     *
     * @return the list of entities.
     */
    List<Formateur> findAll();

    /**
     * Get all the formateurs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Formateur> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" formateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Formateur> findOne(Long id);

    /**
     * Delete the "id" formateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
