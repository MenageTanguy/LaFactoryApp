package com.lafactory.app.service.impl;

import com.lafactory.app.service.OrdinateurService;
import com.lafactory.app.domain.Ordinateur;
import com.lafactory.app.repository.OrdinateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Ordinateur}.
 */
@Service
@Transactional
public class OrdinateurServiceImpl implements OrdinateurService {

    private final Logger log = LoggerFactory.getLogger(OrdinateurServiceImpl.class);

    private final OrdinateurRepository ordinateurRepository;

    public OrdinateurServiceImpl(OrdinateurRepository ordinateurRepository) {
        this.ordinateurRepository = ordinateurRepository;
    }

    /**
     * Save a ordinateur.
     *
     * @param ordinateur the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Ordinateur save(Ordinateur ordinateur) {
        log.debug("Request to save Ordinateur : {}", ordinateur);
        return ordinateurRepository.save(ordinateur);
    }

    /**
     * Get all the ordinateurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Ordinateur> findAll() {
        log.debug("Request to get all Ordinateurs");
        return ordinateurRepository.findAll();
    }

    /**
     * Get one ordinateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ordinateur> findOne(Long id) {
        log.debug("Request to get Ordinateur : {}", id);
        return ordinateurRepository.findById(id);
    }

    /**
     * Delete the ordinateur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ordinateur : {}", id);
        ordinateurRepository.deleteById(id);
    }
}
