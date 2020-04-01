package com.lafactory.app.service;

import com.lafactory.app.domain.Technicien;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Technicien}.
 */
public interface TechnicienService {

    /**
     * Save a technicien.
     *
     * @param technicien the entity to save.
     * @return the persisted entity.
     */
    Technicien save(Technicien technicien);

    /**
     * Get all the techniciens.
     *
     * @return the list of entities.
     */
    List<Technicien> findAll();

    /**
     * Get the "id" technicien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Technicien> findOne(Long id);

    /**
     * Delete the "id" technicien.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
