package com.lafactory.app.web.rest;

import com.lafactory.app.domain.Cursus;
import com.lafactory.app.service.CursusService;
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
 * REST controller for managing {@link com.lafactory.app.domain.Cursus}.
 */
@RestController
@RequestMapping("/api")
public class CursusResource {

    private final Logger log = LoggerFactory.getLogger(CursusResource.class);

    private static final String ENTITY_NAME = "cursus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CursusService cursusService;

    public CursusResource(CursusService cursusService) {
        this.cursusService = cursusService;
    }

    /**
     * {@code POST  /cursuses} : Create a new cursus.
     *
     * @param cursus the cursus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cursus, or with status {@code 400 (Bad Request)} if the cursus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cursuses")
    public ResponseEntity<Cursus> createCursus(@RequestBody Cursus cursus) throws URISyntaxException {
        log.debug("REST request to save Cursus : {}", cursus);
        if (cursus.getId() != null) {
            throw new BadRequestAlertException("A new cursus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cursus result = cursusService.save(cursus);
        return ResponseEntity.created(new URI("/api/cursuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cursuses} : Updates an existing cursus.
     *
     * @param cursus the cursus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cursus,
     * or with status {@code 400 (Bad Request)} if the cursus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cursus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cursuses")
    public ResponseEntity<Cursus> updateCursus(@RequestBody Cursus cursus) throws URISyntaxException {
        log.debug("REST request to update Cursus : {}", cursus);
        if (cursus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cursus result = cursusService.save(cursus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cursus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cursuses} : get all the cursuses.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cursuses in body.
     */
    @GetMapping("/cursuses")
    public List<Cursus> getAllCursuses(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Cursuses");
        return cursusService.findAll();
    }

    /**
     * {@code GET  /cursuses/:id} : get the "id" cursus.
     *
     * @param id the id of the cursus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cursus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cursuses/{id}")
    public ResponseEntity<Cursus> getCursus(@PathVariable Long id) {
        log.debug("REST request to get Cursus : {}", id);
        Optional<Cursus> cursus = cursusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cursus);
    }

    /**
     * {@code DELETE  /cursuses/:id} : delete the "id" cursus.
     *
     * @param id the id of the cursus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cursuses/{id}")
    public ResponseEntity<Void> deleteCursus(@PathVariable Long id) {
        log.debug("REST request to delete Cursus : {}", id);
        cursusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
