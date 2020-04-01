package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Salle;
import com.lafactory.app.repository.SalleRepository;
import com.lafactory.app.service.SalleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SalleResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SalleResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_COUT = 1F;
    private static final Float UPDATED_COUT = 2F;

    private static final Integer DEFAULT_CAPACITE_MAX = 1;
    private static final Integer UPDATED_CAPACITE_MAX = 2;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private SalleService salleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalleMockMvc;

    private Salle salle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salle createEntity(EntityManager em) {
        Salle salle = new Salle()
            .code(DEFAULT_CODE)
            .cout(DEFAULT_COUT)
            .capaciteMax(DEFAULT_CAPACITE_MAX);
        return salle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salle createUpdatedEntity(EntityManager em) {
        Salle salle = new Salle()
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .capaciteMax(UPDATED_CAPACITE_MAX);
        return salle;
    }

    @BeforeEach
    public void initTest() {
        salle = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalle() throws Exception {
        int databaseSizeBeforeCreate = salleRepository.findAll().size();

        // Create the Salle
        restSalleMockMvc.perform(post("/api/salles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isCreated());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeCreate + 1);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSalle.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testSalle.getCapaciteMax()).isEqualTo(DEFAULT_CAPACITE_MAX);
    }

    @Test
    @Transactional
    public void createSalleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salleRepository.findAll().size();

        // Create the Salle with an existing ID
        salle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalleMockMvc.perform(post("/api/salles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalles() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        // Get all the salleList
        restSalleMockMvc.perform(get("/api/salles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salle.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT.doubleValue())))
            .andExpect(jsonPath("$.[*].capaciteMax").value(hasItem(DEFAULT_CAPACITE_MAX)));
    }
    
    @Test
    @Transactional
    public void getSalle() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        // Get the salle
        restSalleMockMvc.perform(get("/api/salles/{id}", salle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salle.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT.doubleValue()))
            .andExpect(jsonPath("$.capaciteMax").value(DEFAULT_CAPACITE_MAX));
    }

    @Test
    @Transactional
    public void getNonExistingSalle() throws Exception {
        // Get the salle
        restSalleMockMvc.perform(get("/api/salles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalle() throws Exception {
        // Initialize the database
        salleService.save(salle);

        int databaseSizeBeforeUpdate = salleRepository.findAll().size();

        // Update the salle
        Salle updatedSalle = salleRepository.findById(salle.getId()).get();
        // Disconnect from session so that the updates on updatedSalle are not directly saved in db
        em.detach(updatedSalle);
        updatedSalle
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .capaciteMax(UPDATED_CAPACITE_MAX);

        restSalleMockMvc.perform(put("/api/salles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSalle)))
            .andExpect(status().isOk());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSalle.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testSalle.getCapaciteMax()).isEqualTo(UPDATED_CAPACITE_MAX);
    }

    @Test
    @Transactional
    public void updateNonExistingSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();

        // Create the Salle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalleMockMvc.perform(put("/api/salles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalle() throws Exception {
        // Initialize the database
        salleService.save(salle);

        int databaseSizeBeforeDelete = salleRepository.findAll().size();

        // Delete the salle
        restSalleMockMvc.perform(delete("/api/salles/{id}", salle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
