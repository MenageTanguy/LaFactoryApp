package com.lafactory.app.service.impl;

import com.lafactory.app.service.MatiereService;
import com.lafactory.app.domain.Matiere;
import com.lafactory.app.repository.MatiereRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Matiere}.
 */
@Service
@Transactional
public class MatiereServiceImpl implements MatiereService {

    private final Logger log = LoggerFactory.getLogger(MatiereServiceImpl.class);

    private final MatiereRepository matiereRepository;

    public MatiereServiceImpl(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    /**
     * Save a matiere.
     *
     * @param matiere the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Matiere save(Matiere matiere) {
        log.debug("Request to save Matiere : {}", matiere);
        return matiereRepository.save(matiere);
    }

    /**
     * Get all the matieres.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Matiere> findAll() {
        log.debug("Request to get all Matieres");
        return matiereRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the matieres with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Matiere> findAllWithEagerRelationships(Pageable pageable) {
        return matiereRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one matiere by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Matiere> findOne(Long id) {
        log.debug("Request to get Matiere : {}", id);
        return matiereRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the matiere by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Matiere : {}", id);
        matiereRepository.deleteById(id);
    }
}
