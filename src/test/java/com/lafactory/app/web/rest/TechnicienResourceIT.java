package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Technicien;
import com.lafactory.app.repository.TechnicienRepository;
import com.lafactory.app.service.TechnicienService;

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
 * Integration tests for the {@link TechnicienResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TechnicienResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_COORDONNEES = "AAAAAAAAAA";
    private static final String UPDATED_COORDONNEES = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_RUE = 1;
    private static final Integer UPDATED_NUMERO_RUE = 2;

    private static final String DEFAULT_RUE = "AAAAAAAAAA";
    private static final String UPDATED_RUE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private TechnicienService technicienService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTechnicienMockMvc;

    private Technicien technicien;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technicien createEntity(EntityManager em) {
        Technicien technicien = new Technicien()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .coordonnees(DEFAULT_COORDONNEES)
            .numeroRue(DEFAULT_NUMERO_RUE)
            .rue(DEFAULT_RUE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .ville(DEFAULT_VILLE);
        return technicien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technicien createUpdatedEntity(EntityManager em) {
        Technicien technicien = new Technicien()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);
        return technicien;
    }

    @BeforeEach
    public void initTest() {
        technicien = createEntity(em);
    }

    @Test
    @Transactional
    public void createTechnicien() throws Exception {
        int databaseSizeBeforeCreate = technicienRepository.findAll().size();

        // Create the Technicien
        restTechnicienMockMvc.perform(post("/api/techniciens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(technicien)))
            .andExpect(status().isCreated());

        // Validate the Technicien in the database
        List<Technicien> technicienList = technicienRepository.findAll();
        assertThat(technicienList).hasSize(databaseSizeBeforeCreate + 1);
        Technicien testTechnicien = technicienList.get(technicienList.size() - 1);
        assertThat(testTechnicien.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTechnicien.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testTechnicien.getCoordonnees()).isEqualTo(DEFAULT_COORDONNEES);
        assertThat(testTechnicien.getNumeroRue()).isEqualTo(DEFAULT_NUMERO_RUE);
        assertThat(testTechnicien.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testTechnicien.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testTechnicien.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createTechnicienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = technicienRepository.findAll().size();

        // Create the Technicien with an existing ID
        technicien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnicienMockMvc.perform(post("/api/techniciens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(technicien)))
            .andExpect(status().isBadRequest());

        // Validate the Technicien in the database
        List<Technicien> technicienList = technicienRepository.findAll();
        assertThat(technicienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTechniciens() throws Exception {
        // Initialize the database
        technicienRepository.saveAndFlush(technicien);

        // Get all the technicienList
        restTechnicienMockMvc.perform(get("/api/techniciens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technicien.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].coordonnees").value(hasItem(DEFAULT_COORDONNEES)))
            .andExpect(jsonPath("$.[*].numeroRue").value(hasItem(DEFAULT_NUMERO_RUE)))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)));
    }
    
    @Test
    @Transactional
    public void getTechnicien() throws Exception {
        // Initialize the database
        technicienRepository.saveAndFlush(technicien);

        // Get the technicien
        restTechnicienMockMvc.perform(get("/api/techniciens/{id}", technicien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(technicien.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.coordonnees").value(DEFAULT_COORDONNEES))
            .andExpect(jsonPath("$.numeroRue").value(DEFAULT_NUMERO_RUE))
            .andExpect(jsonPath("$.rue").value(DEFAULT_RUE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE));
    }

    @Test
    @Transactional
    public void getNonExistingTechnicien() throws Exception {
        // Get the technicien
        restTechnicienMockMvc.perform(get("/api/techniciens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTechnicien() throws Exception {
        // Initialize the database
        technicienService.save(technicien);

        int databaseSizeBeforeUpdate = technicienRepository.findAll().size();

        // Update the technicien
        Technicien updatedTechnicien = technicienRepository.findById(technicien.getId()).get();
        // Disconnect from session so that the updates on updatedTechnicien are not directly saved in db
        em.detach(updatedTechnicien);
        updatedTechnicien
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);

        restTechnicienMockMvc.perform(put("/api/techniciens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTechnicien)))
            .andExpect(status().isOk());

        // Validate the Technicien in the database
        List<Technicien> technicienList = technicienRepository.findAll();
        assertThat(technicienList).hasSize(databaseSizeBeforeUpdate);
        Technicien testTechnicien = technicienList.get(technicienList.size() - 1);
        assertThat(testTechnicien.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTechnicien.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testTechnicien.getCoordonnees()).isEqualTo(UPDATED_COORDONNEES);
        assertThat(testTechnicien.getNumeroRue()).isEqualTo(UPDATED_NUMERO_RUE);
        assertThat(testTechnicien.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testTechnicien.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testTechnicien.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTechnicien() throws Exception {
        int databaseSizeBeforeUpdate = technicienRepository.findAll().size();

        // Create the Technicien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicienMockMvc.perform(put("/api/techniciens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(technicien)))
            .andExpect(status().isBadRequest());

        // Validate the Technicien in the database
        List<Technicien> technicienList = technicienRepository.findAll();
        assertThat(technicienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTechnicien() throws Exception {
        // Initialize the database
        technicienService.save(technicien);

        int databaseSizeBeforeDelete = technicienRepository.findAll().size();

        // Delete the technicien
        restTechnicienMockMvc.perform(delete("/api/techniciens/{id}", technicien.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Technicien> technicienList = technicienRepository.findAll();
        assertThat(technicienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
