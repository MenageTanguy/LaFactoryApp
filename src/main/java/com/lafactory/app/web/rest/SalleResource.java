package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Salle;
import com.lafactory.app.service.SalleService;
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
 * REST controller for managing {@link com.lafactory.app.domain.Salle}.
 */
@RestController
@RequestMapping("/api")
public class SalleResource {

    private final Logger log = LoggerFactory.getLogger(SalleResource.class);

    private static final String ENTITY_NAME = "salle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalleService salleService;

    public SalleResource(SalleService salleService) {
        this.salleService = salleService;
    }

    /**
     * {@code POST  /salles} : Create a new salle.
     *
     * @param salle the salle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salle, or with status {@code 400 (Bad Request)} if the salle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/salles")
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) throws URISyntaxException {
        log.debug("REST request to save Salle : {}", salle);
        if (salle.getId() != null) {
            throw new BadRequestAlertException("A new salle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Salle result = salleService.save(salle);
        return ResponseEntity.created(new URI("/api/salles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /salles} : Updates an existing salle.
     *
     * @param salle the salle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salle,
     * or with status {@code 400 (Bad Request)} if the salle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/salles")
    public ResponseEntity<Salle> updateSalle(@RequestBody Salle salle) throws URISyntaxException {
        log.debug("REST request to update Salle : {}", salle);
        if (salle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Salle result = salleService.save(salle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /salles} : get all the salles.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salles in body.
     */
    @GetMapping("/salles")
    public List<Salle> getAllSalles(@RequestParam(required = false) String filter) {
        if ("projecteur-is-null".equals(filter)) {
            log.debug("REST request to get all Salles where projecteur is null");
            return salleService.findAllWhereProjecteurIsNull();
        }
        if ("cursus-is-null".equals(filter)) {
            log.debug("REST request to get all Salles where cursus is null");
            return salleService.findAllWhereCursusIsNull();
        }
        log.debug("REST request to get all Salles");
        return salleService.findAll();
    }

    /**
     * {@code GET  /salles/:id} : get the "id" salle.
     *
     * @param id the id of the salle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salles/{id}")
    public ResponseEntity<Salle> getSalle(@PathVariable Long id) {
        log.debug("REST request to get Salle : {}", id);
        Optional<Salle> salle = salleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salle);
    }

    /**
     * {@code DELETE  /salles/:id} : delete the "id" salle.
     *
     * @param id the id of the salle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/salles/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        log.debug("REST request to delete Salle : {}", id);
        salleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
