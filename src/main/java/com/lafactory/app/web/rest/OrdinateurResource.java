package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Ordinateur;
import com.lafactory.app.service.OrdinateurService;
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
 * REST controller for managing {@link com.lafactory.app.domain.Ordinateur}.
 */
@RestController
@RequestMapping("/api")
public class OrdinateurResource {

    private final Logger log = LoggerFactory.getLogger(OrdinateurResource.class);

    private static final String ENTITY_NAME = "ordinateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdinateurService ordinateurService;

    public OrdinateurResource(OrdinateurService ordinateurService) {
        this.ordinateurService = ordinateurService;
    }

    /**
     * {@code POST  /ordinateurs} : Create a new ordinateur.
     *
     * @param ordinateur the ordinateur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordinateur, or with status {@code 400 (Bad Request)} if the ordinateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordinateurs")
    public ResponseEntity<Ordinateur> createOrdinateur(@RequestBody Ordinateur ordinateur) throws URISyntaxException {
        log.debug("REST request to save Ordinateur : {}", ordinateur);
        if (ordinateur.getId() != null) {
            throw new BadRequestAlertException("A new ordinateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ordinateur result = ordinateurService.save(ordinateur);
        return ResponseEntity.created(new URI("/api/ordinateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ordinateurs} : Updates an existing ordinateur.
     *
     * @param ordinateur the ordinateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordinateur,
     * or with status {@code 400 (Bad Request)} if the ordinateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordinateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordinateurs")
    public ResponseEntity<Ordinateur> updateOrdinateur(@RequestBody Ordinateur ordinateur) throws URISyntaxException {
        log.debug("REST request to update Ordinateur : {}", ordinateur);
        if (ordinateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ordinateur result = ordinateurService.save(ordinateur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ordinateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ordinateurs} : get all the ordinateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordinateurs in body.
     */
    @GetMapping("/ordinateurs")
    public List<Ordinateur> getAllOrdinateurs() {
        log.debug("REST request to get all Ordinateurs");
        return ordinateurService.findAll();
    }

    /**
     * {@code GET  /ordinateurs/:id} : get the "id" ordinateur.
     *
     * @param id the id of the ordinateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordinateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordinateurs/{id}")
    public ResponseEntity<Ordinateur> getOrdinateur(@PathVariable Long id) {
        log.debug("REST request to get Ordinateur : {}", id);
        Optional<Ordinateur> ordinateur = ordinateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordinateur);
    }

    /**
     * {@code DELETE  /ordinateurs/:id} : delete the "id" ordinateur.
     *
     * @param id the id of the ordinateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordinateurs/{id}")
    public ResponseEntity<Void> deleteOrdinateur(@PathVariable Long id) {
        log.debug("REST request to delete Ordinateur : {}", id);
        ordinateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
