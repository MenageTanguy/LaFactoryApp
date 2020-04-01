package com.lafactory.app.service;

import com.lafactory.app.domain.Module;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Module}.
 */
public interface ModuleService {

    /**
     * Save a module.
     *
     * @param module the entity to save.
     * @return the persisted entity.
     */
    Module save(Module module);

    /**
     * Get all the modules.
     *
     * @return the list of entities.
     */
    List<Module> findAll();

    /**
     * Get all the modules with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Module> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" module.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Module> findOne(Long id);

    /**
     * Delete the "id" module.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
