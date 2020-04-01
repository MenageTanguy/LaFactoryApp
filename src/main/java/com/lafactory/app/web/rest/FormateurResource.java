package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Formateur;
import com.lafactory.app.service.FormateurService;
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
 * REST controller for managing {@link com.lafactory.app.domain.Formateur}.
 */
@RestController
@RequestMapping("/api")
public class FormateurResource {

    private final Logger log = LoggerFactory.getLogger(FormateurResource.class);

    private static final String ENTITY_NAME = "formateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormateurService formateurService;

    public FormateurResource(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    /**
     * {@code POST  /formateurs} : Create a new formateur.
     *
     * @param formateur the formateur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formateur, or with status {@code 400 (Bad Request)} if the formateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formateurs")
    public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) throws URISyntaxException {
        log.debug("REST request to save Formateur : {}", formateur);
        if (formateur.getId() != null) {
            throw new BadRequestAlertException("A new formateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Formateur result = formateurService.save(formateur);
        return ResponseEntity.created(new URI("/api/formateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formateurs} : Updates an existing formateur.
     *
     * @param formateur the formateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formateur,
     * or with status {@code 400 (Bad Request)} if the formateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formateurs")
    public ResponseEntity<Formateur> updateFormateur(@RequestBody Formateur formateur) throws URISyntaxException {
        log.debug("REST request to update Formateur : {}", formateur);
        if (formateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Formateur result = formateurService.save(formateur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formateurs} : get all the formateurs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formateurs in body.
     */
    @GetMapping("/formateurs")
    public List<Formateur> getAllFormateurs(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Formateurs");
        return formateurService.findAll();
    }

    /**
     * {@code GET  /formateurs/:id} : get the "id" formateur.
     *
     * @param id the id of the formateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formateurs/{id}")
    public ResponseEntity<Formateur> getFormateur(@PathVariable Long id) {
        log.debug("REST request to get Formateur : {}", id);
        Optional<Formateur> formateur = formateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formateur);
    }

    /**
     * {@code DELETE  /formateurs/:id} : delete the "id" formateur.
     *
     * @param id the id of the formateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formateurs/{id}")
    public ResponseEntity<Void> deleteFormateur(@PathVariable Long id) {
        log.debug("REST request to delete Formateur : {}", id);
        formateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
