package com.lafactory.app.service.impl;

import com.lafactory.app.service.FormateurService;
import com.lafactory.app.domain.Formateur;
import com.lafactory.app.repository.FormateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Formateur}.
 */
@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {

    private final Logger log = LoggerFactory.getLogger(FormateurServiceImpl.class);

    private final FormateurRepository formateurRepository;

    public FormateurServiceImpl(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    /**
     * Save a formateur.
     *
     * @param formateur the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Formateur save(Formateur formateur) {
        log.debug("Request to save Formateur : {}", formateur);
        return formateurRepository.save(formateur);
    }

    /**
     * Get all the formateurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Formateur> findAll() {
        log.debug("Request to get all Formateurs");
        return formateurRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the formateurs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Formateur> findAllWithEagerRelationships(Pageable pageable) {
        return formateurRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one formateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Formateur> findOne(Long id) {
        log.debug("Request to get Formateur : {}", id);
        return formateurRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the formateur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Formateur : {}", id);
        formateurRepository.deleteById(id);
    }
}
