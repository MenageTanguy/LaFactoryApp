package com.lafactory.app.service.impl;

import com.lafactory.app.service.StagiaireService;
import com.lafactory.app.domain.Stagiaire;
import com.lafactory.app.repository.StagiaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Stagiaire}.
 */
@Service
@Transactional
public class StagiaireServiceImpl implements StagiaireService {

    private final Logger log = LoggerFactory.getLogger(StagiaireServiceImpl.class);

    private final StagiaireRepository stagiaireRepository;

    public StagiaireServiceImpl(StagiaireRepository stagiaireRepository) {
        this.stagiaireRepository = stagiaireRepository;
    }

    /**
     * Save a stagiaire.
     *
     * @param stagiaire the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Stagiaire save(Stagiaire stagiaire) {
        log.debug("Request to save Stagiaire : {}", stagiaire);
        return stagiaireRepository.save(stagiaire);
    }

    /**
     * Get all the stagiaires.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Stagiaire> findAll() {
        log.debug("Request to get all Stagiaires");
        return stagiaireRepository.findAll();
    }


    /**
     *  Get all the stagiaires where Ordinateur is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Stagiaire> findAllWhereOrdinateurIsNull() {
        log.debug("Request to get all stagiaires where Ordinateur is null");
        return StreamSupport
            .stream(stagiaireRepository.findAll().spliterator(), false)
            .filter(stagiaire -> stagiaire.getOrdinateur() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one stagiaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Stagiaire> findOne(Long id) {
        log.debug("Request to get Stagiaire : {}", id);
        return stagiaireRepository.findById(id);
    }

    /**
     * Delete the stagiaire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stagiaire : {}", id);
        stagiaireRepository.deleteById(id);
    }
}
