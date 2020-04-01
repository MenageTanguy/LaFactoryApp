package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Stagiaire;
import com.lafactory.app.service.StagiaireService;
import com.lafactory.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.lafactory.app.domain.Stagiaire}.
 */
@RestController
@RequestMapping("/api")
public class StagiaireResource {

    private final Logger log = LoggerFactory.getLogger(StagiaireResource.class);

    private static final String ENTITY_NAME = "stagiaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StagiaireService stagiaireService;

    public StagiaireResource(StagiaireService stagiaireService) {
        this.stagiaireService = stagiaireService;
    }

    /**
     * {@code POST  /stagiaires} : Create a new stagiaire.
     *
     * @param stagiaire the stagiaire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stagiaire, or with status {@code 400 (Bad Request)} if the stagiaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stagiaires")
    public ResponseEntity<Stagiaire> createStagiaire(@RequestBody Stagiaire stagiaire) throws URISyntaxException {
        log.debug("REST request to save Stagiaire : {}", stagiaire);
        if (stagiaire.getId() != null) {
            throw new BadRequestAlertException("A new stagiaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stagiaire result = stagiaireService.save(stagiaire);
        return ResponseEntity.created(new URI("/api/stagiaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stagiaires} : Updates an existing stagiaire.
     *
     * @param stagiaire the stagiaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stagiaire,
     * or with status {@code 400 (Bad Request)} if the stagiaire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stagiaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stagiaires")
    public ResponseEntity<Stagiaire> updateStagiaire(@RequestBody Stagiaire stagiaire) throws URISyntaxException {
        log.debug("REST request to update Stagiaire : {}", stagiaire);
        if (stagiaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stagiaire result = stagiaireService.save(stagiaire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stagiaire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stagiaires} : get all the stagiaires.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stagiaires in body.
     */
    @GetMapping("/stagiaires")
    public List<Stagiaire> getAllStagiaires(@RequestParam(required = false) String filter) {
        if ("ordinateur-is-null".equals(filter)) {
            log.debug("REST request to get all Stagiaires where ordinateur is null");
            return stagiaireService.findAllWhereOrdinateurIsNull();
        }
        log.debug("REST request to get all Stagiaires");
        return stagiaireService.findAll();
    }

    /**
     * {@code GET  /stagiaires/:id} : get the "id" stagiaire.
     *
     * @param id the id of the stagiaire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stagiaire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stagiaires/{id}")
    public ResponseEntity<Stagiaire> getStagiaire(@PathVariable Long id) {
        log.debug("REST request to get Stagiaire : {}", id);
        Optional<Stagiaire> stagiaire = stagiaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stagiaire);
    }

    /**
     * {@code DELETE  /stagiaires/:id} : delete the "id" stagiaire.
     *
     * @param id the id of the stagiaire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stagiaires/{id}")
    public ResponseEntity<Void> deleteStagiaire(@PathVariable Long id) {
        log.debug("REST request to delete Stagiaire : {}", id);
        stagiaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
