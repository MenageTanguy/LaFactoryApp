package com.lafactory.app.service;

import com.lafactory.app.domain.Cursus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Cursus}.
 */
public interface CursusService {

    /**
     * Save a cursus.
     *
     * @param cursus the entity to save.
     * @return the persisted entity.
     */
    Cursus save(Cursus cursus);

    /**
     * Get all the cursuses.
     *
     * @return the list of entities.
     */
    List<Cursus> findAll();

    /**
     * Get all the cursuses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Cursus> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" cursus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Cursus> findOne(Long id);

    /**
     * Delete the "id" cursus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
