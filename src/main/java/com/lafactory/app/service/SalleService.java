package com.lafactory.app.service;

import com.lafactory.app.domain.Salle;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Salle}.
 */
public interface SalleService {

    /**
     * Save a salle.
     *
     * @param salle the entity to save.
     * @return the persisted entity.
     */
    Salle save(Salle salle);

    /**
     * Get all the salles.
     *
     * @return the list of entities.
     */
    List<Salle> findAll();
    /**
     * Get all the SalleDTO where Projecteur is {@code null}.
     *
     * @return the list of entities.
     */
    List<Salle> findAllWhereProjecteurIsNull();
    /**
     * Get all the SalleDTO where Cursus is {@code null}.
     *
     * @return the list of entities.
     */
    List<Salle> findAllWhereCursusIsNull();

    /**
     * Get the "id" salle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Salle> findOne(Long id);

    /**
     * Delete the "id" salle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
