package com.lafactory.app.service.impl;

import com.lafactory.app.service.CursusService;
import com.lafactory.app.domain.Cursus;
import com.lafactory.app.repository.CursusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Cursus}.
 */
@Service
@Transactional
public class CursusServiceImpl implements CursusService {

    private final Logger log = LoggerFactory.getLogger(CursusServiceImpl.class);

    private final CursusRepository cursusRepository;

    public CursusServiceImpl(CursusRepository cursusRepository) {
        this.cursusRepository = cursusRepository;
    }

    /**
     * Save a cursus.
     *
     * @param cursus the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Cursus save(Cursus cursus) {
        log.debug("Request to save Cursus : {}", cursus);
        return cursusRepository.save(cursus);
    }

    /**
     * Get all the cursuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Cursus> findAll() {
        log.debug("Request to get all Cursuses");
        return cursusRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the cursuses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Cursus> findAllWithEagerRelationships(Pageable pageable) {
        return cursusRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one cursus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cursus> findOne(Long id) {
        log.debug("Request to get Cursus : {}", id);
        return cursusRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the cursus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cursus : {}", id);
        cursusRepository.deleteById(id);
    }
}
