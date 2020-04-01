package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Gestionnaire;
import com.lafactory.app.service.GestionnaireService;
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

/**
 * REST controller for managing {@link com.lafactory.app.domain.Gestionnaire}.
 */
@RestController
@RequestMapping("/api")
public class GestionnaireResource {

    private final Logger log = LoggerFactory.getLogger(GestionnaireResource.class);

    private static final String ENTITY_NAME = "gestionnaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GestionnaireService gestionnaireService;

    public GestionnaireResource(GestionnaireService gestionnaireService) {
        this.gestionnaireService = gestionnaireService;
    }

    /**
     * {@code POST  /gestionnaires} : Create a new gestionnaire.
     *
     * @param gestionnaire the gestionnaire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gestionnaire, or with status {@code 400 (Bad Request)} if the gestionnaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gestionnaires")
    public ResponseEntity<Gestionnaire> createGestionnaire(@RequestBody Gestionnaire gestionnaire) throws URISyntaxException {
        log.debug("REST request to save Gestionnaire : {}", gestionnaire);
        if (gestionnaire.getId() != null) {
            throw new BadRequestAlertException("A new gestionnaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gestionnaire result = gestionnaireService.save(gestionnaire);
        return ResponseEntity.created(new URI("/api/gestionnaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gestionnaires} : Updates an existing gestionnaire.
     *
     * @param gestionnaire the gestionnaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gestionnaire,
     * or with status {@code 400 (Bad Request)} if the gestionnaire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gestionnaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gestionnaires")
    public ResponseEntity<Gestionnaire> updateGestionnaire(@RequestBody Gestionnaire gestionnaire) throws URISyntaxException {
        log.debug("REST request to update Gestionnaire : {}", gestionnaire);
        if (gestionnaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gestionnaire result = gestionnaireService.save(gestionnaire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gestionnaire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gestionnaires} : get all the gestionnaires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gestionnaires in body.
     */
    @GetMapping("/gestionnaires")
    public List<Gestionnaire> getAllGestionnaires() {
        log.debug("REST request to get all Gestionnaires");
        return gestionnaireService.findAll();
    }

    /**
     * {@code GET  /gestionnaires/:id} : get the "id" gestionnaire.
     *
     * @param id the id of the gestionnaire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gestionnaire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gestionnaires/{id}")
    public ResponseEntity<Gestionnaire> getGestionnaire(@PathVariable Long id) {
        log.debug("REST request to get Gestionnaire : {}", id);
        Optional<Gestionnaire> gestionnaire = gestionnaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gestionnaire);
    }

    /**
     * {@code DELETE  /gestionnaires/:id} : delete the "id" gestionnaire.
     *
     * @param id the id of the gestionnaire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gestionnaires/{id}")
    public ResponseEntity<Void> deleteGestionnaire(@PathVariable Long id) {
        log.debug("REST request to delete Gestionnaire : {}", id);
        gestionnaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
