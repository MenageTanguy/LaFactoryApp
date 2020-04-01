package com.lafactory.app.service.impl;

import com.lafactory.app.service.GestionnaireService;
import com.lafactory.app.domain.Gestionnaire;
import com.lafactory.app.repository.GestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Gestionnaire}.
 */
@Service
@Transactional
public class GestionnaireServiceImpl implements GestionnaireService {

    private final Logger log = LoggerFactory.getLogger(GestionnaireServiceImpl.class);

    private final GestionnaireRepository gestionnaireRepository;

    public GestionnaireServiceImpl(GestionnaireRepository gestionnaireRepository) {
        this.gestionnaireRepository = gestionnaireRepository;
    }

    /**
     * Save a gestionnaire.
     *
     * @param gestionnaire the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Gestionnaire save(Gestionnaire gestionnaire) {
        log.debug("Request to save Gestionnaire : {}", gestionnaire);
        return gestionnaireRepository.save(gestionnaire);
    }

    /**
     * Get all the gestionnaires.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Gestionnaire> findAll() {
        log.debug("Request to get all Gestionnaires");
        return gestionnaireRepository.findAll();
    }

    /**
     * Get one gestionnaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Gestionnaire> findOne(Long id) {
        log.debug("Request to get Gestionnaire : {}", id);
        return gestionnaireRepository.findById(id);
    }

    /**
     * Delete the gestionnaire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gestionnaire : {}", id);
        gestionnaireRepository.deleteById(id);
    }
}
