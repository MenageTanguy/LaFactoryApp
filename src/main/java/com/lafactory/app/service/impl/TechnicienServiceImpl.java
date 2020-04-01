package com.lafactory.app.service.impl;

import com.lafactory.app.service.TechnicienService;
import com.lafactory.app.domain.Technicien;
import com.lafactory.app.repository.TechnicienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Technicien}.
 */
@Service
@Transactional
public class TechnicienServiceImpl implements TechnicienService {

    private final Logger log = LoggerFactory.getLogger(TechnicienServiceImpl.class);

    private final TechnicienRepository technicienRepository;

    public TechnicienServiceImpl(TechnicienRepository technicienRepository) {
        this.technicienRepository = technicienRepository;
    }

    /**
     * Save a technicien.
     *
     * @param technicien the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Technicien save(Technicien technicien) {
        log.debug("Request to save Technicien : {}", technicien);
        return technicienRepository.save(technicien);
    }

    /**
     * Get all the techniciens.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Technicien> findAll() {
        log.debug("Request to get all Techniciens");
        return technicienRepository.findAll();
    }

    /**
     * Get one technicien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Technicien> findOne(Long id) {
        log.debug("Request to get Technicien : {}", id);
        return technicienRepository.findById(id);
    }

    /**
     * Delete the technicien by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Technicien : {}", id);
        technicienRepository.deleteById(id);
    }
}
