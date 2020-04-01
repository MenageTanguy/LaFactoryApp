package com.lafactory.app.service.impl;

import com.lafactory.app.service.ProjecteurService;
import com.lafactory.app.domain.Projecteur;
import com.lafactory.app.repository.ProjecteurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Projecteur}.
 */
@Service
@Transactional
public class ProjecteurServiceImpl implements ProjecteurService {

    private final Logger log = LoggerFactory.getLogger(ProjecteurServiceImpl.class);

    private final ProjecteurRepository projecteurRepository;

    public ProjecteurServiceImpl(ProjecteurRepository projecteurRepository) {
        this.projecteurRepository = projecteurRepository;
    }

    /**
     * Save a projecteur.
     *
     * @param projecteur the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Projecteur save(Projecteur projecteur) {
        log.debug("Request to save Projecteur : {}", projecteur);
        return projecteurRepository.save(projecteur);
    }

    /**
     * Get all the projecteurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Projecteur> findAll() {
        log.debug("Request to get all Projecteurs");
        return projecteurRepository.findAll();
    }

    /**
     * Get one projecteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Projecteur> findOne(Long id) {
        log.debug("Request to get Projecteur : {}", id);
        return projecteurRepository.findById(id);
    }

    /**
     * Delete the projecteur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Projecteur : {}", id);
        projecteurRepository.deleteById(id);
    }
}
