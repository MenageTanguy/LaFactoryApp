package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Stagiaire;
import com.lafactory.app.repository.StagiaireRepository;
import com.lafactory.app.service.StagiaireService;

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
 * Integration tests for the {@link StagiaireResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class StagiaireResourceIT {

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
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStagiaireMockMvc;

    private Stagiaire stagiaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stagiaire createEntity(EntityManager em) {
        Stagiaire stagiaire = new Stagiaire()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .coordonnees(DEFAULT_COORDONNEES)
            .numeroRue(DEFAULT_NUMERO_RUE)
            .rue(DEFAULT_RUE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .ville(DEFAULT_VILLE);
        return stagiaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stagiaire createUpdatedEntity(EntityManager em) {
        Stagiaire stagiaire = new Stagiaire()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);
        return stagiaire;
    }

    @BeforeEach
    public void initTest() {
        stagiaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createStagiaire() throws Exception {
        int databaseSizeBeforeCreate = stagiaireRepository.findAll().size();

        // Create the Stagiaire
        restStagiaireMockMvc.perform(post("/api/stagiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stagiaire)))
            .andExpect(status().isCreated());

        // Validate the Stagiaire in the database
        List<Stagiaire> stagiaireList = stagiaireRepository.findAll();
        assertThat(stagiaireList).hasSize(databaseSizeBeforeCreate + 1);
        Stagiaire testStagiaire = stagiaireList.get(stagiaireList.size() - 1);
        assertThat(testStagiaire.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testStagiaire.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testStagiaire.getCoordonnees()).isEqualTo(DEFAULT_COORDONNEES);
        assertThat(testStagiaire.getNumeroRue()).isEqualTo(DEFAULT_NUMERO_RUE);
        assertThat(testStagiaire.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testStagiaire.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testStagiaire.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createStagiaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stagiaireRepository.findAll().size();

        // Create the Stagiaire with an existing ID
        stagiaire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStagiaireMockMvc.perform(post("/api/stagiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stagiaire)))
            .andExpect(status().isBadRequest());

        // Validate the Stagiaire in the database
        List<Stagiaire> stagiaireList = stagiaireRepository.findAll();
        assertThat(stagiaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStagiaires() throws Exception {
        // Initialize the database
        stagiaireRepository.saveAndFlush(stagiaire);

        // Get all the stagiaireList
        restStagiaireMockMvc.perform(get("/api/stagiaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stagiaire.getId().intValue())))
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
    public void getStagiaire() throws Exception {
        // Initialize the database
        stagiaireRepository.saveAndFlush(stagiaire);

        // Get the stagiaire
        restStagiaireMockMvc.perform(get("/api/stagiaires/{id}", stagiaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stagiaire.getId().intValue()))
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
    public void getNonExistingStagiaire() throws Exception {
        // Get the stagiaire
        restStagiaireMockMvc.perform(get("/api/stagiaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStagiaire() throws Exception {
        // Initialize the database
        stagiaireService.save(stagiaire);

        int databaseSizeBeforeUpdate = stagiaireRepository.findAll().size();

        // Update the stagiaire
        Stagiaire updatedStagiaire = stagiaireRepository.findById(stagiaire.getId()).get();
        // Disconnect from session so that the updates on updatedStagiaire are not directly saved in db
        em.detach(updatedStagiaire);
        updatedStagiaire
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);

        restStagiaireMockMvc.perform(put("/api/stagiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStagiaire)))
            .andExpect(status().isOk());

        // Validate the Stagiaire in the database
        List<Stagiaire> stagiaireList = stagiaireRepository.findAll();
        assertThat(stagiaireList).hasSize(databaseSizeBeforeUpdate);
        Stagiaire testStagiaire = stagiaireList.get(stagiaireList.size() - 1);
        assertThat(testStagiaire.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testStagiaire.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testStagiaire.getCoordonnees()).isEqualTo(UPDATED_COORDONNEES);
        assertThat(testStagiaire.getNumeroRue()).isEqualTo(UPDATED_NUMERO_RUE);
        assertThat(testStagiaire.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testStagiaire.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testStagiaire.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingStagiaire() throws Exception {
        int databaseSizeBeforeUpdate = stagiaireRepository.findAll().size();

        // Create the Stagiaire

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStagiaireMockMvc.perform(put("/api/stagiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stagiaire)))
            .andExpect(status().isBadRequest());

        // Validate the Stagiaire in the database
        List<Stagiaire> stagiaireList = stagiaireRepository.findAll();
        assertThat(stagiaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStagiaire() throws Exception {
        // Initialize the database
        stagiaireService.save(stagiaire);

        int databaseSizeBeforeDelete = stagiaireRepository.findAll().size();

        // Delete the stagiaire
        restStagiaireMockMvc.perform(delete("/api/stagiaires/{id}", stagiaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stagiaire> stagiaireList = stagiaireRepository.findAll();
        assertThat(stagiaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
