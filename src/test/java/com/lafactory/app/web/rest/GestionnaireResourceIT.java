package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Gestionnaire;
import com.lafactory.app.repository.GestionnaireRepository;
import com.lafactory.app.service.GestionnaireService;

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
 * Integration tests for the {@link GestionnaireResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class GestionnaireResourceIT {

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
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGestionnaireMockMvc;

    private Gestionnaire gestionnaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestionnaire createEntity(EntityManager em) {
        Gestionnaire gestionnaire = new Gestionnaire()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .coordonnees(DEFAULT_COORDONNEES)
            .numeroRue(DEFAULT_NUMERO_RUE)
            .rue(DEFAULT_RUE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .ville(DEFAULT_VILLE);
        return gestionnaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestionnaire createUpdatedEntity(EntityManager em) {
        Gestionnaire gestionnaire = new Gestionnaire()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);
        return gestionnaire;
    }

    @BeforeEach
    public void initTest() {
        gestionnaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createGestionnaire() throws Exception {
        int databaseSizeBeforeCreate = gestionnaireRepository.findAll().size();

        // Create the Gestionnaire
        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isCreated());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeCreate + 1);
        Gestionnaire testGestionnaire = gestionnaireList.get(gestionnaireList.size() - 1);
        assertThat(testGestionnaire.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testGestionnaire.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testGestionnaire.getCoordonnees()).isEqualTo(DEFAULT_COORDONNEES);
        assertThat(testGestionnaire.getNumeroRue()).isEqualTo(DEFAULT_NUMERO_RUE);
        assertThat(testGestionnaire.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testGestionnaire.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testGestionnaire.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createGestionnaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gestionnaireRepository.findAll().size();

        // Create the Gestionnaire with an existing ID
        gestionnaire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGestionnaires() throws Exception {
        // Initialize the database
        gestionnaireRepository.saveAndFlush(gestionnaire);

        // Get all the gestionnaireList
        restGestionnaireMockMvc.perform(get("/api/gestionnaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gestionnaire.getId().intValue())))
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
    public void getGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireRepository.saveAndFlush(gestionnaire);

        // Get the gestionnaire
        restGestionnaireMockMvc.perform(get("/api/gestionnaires/{id}", gestionnaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gestionnaire.getId().intValue()))
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
    public void getNonExistingGestionnaire() throws Exception {
        // Get the gestionnaire
        restGestionnaireMockMvc.perform(get("/api/gestionnaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireService.save(gestionnaire);

        int databaseSizeBeforeUpdate = gestionnaireRepository.findAll().size();

        // Update the gestionnaire
        Gestionnaire updatedGestionnaire = gestionnaireRepository.findById(gestionnaire.getId()).get();
        // Disconnect from session so that the updates on updatedGestionnaire are not directly saved in db
        em.detach(updatedGestionnaire);
        updatedGestionnaire
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);

        restGestionnaireMockMvc.perform(put("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGestionnaire)))
            .andExpect(status().isOk());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeUpdate);
        Gestionnaire testGestionnaire = gestionnaireList.get(gestionnaireList.size() - 1);
        assertThat(testGestionnaire.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testGestionnaire.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testGestionnaire.getCoordonnees()).isEqualTo(UPDATED_COORDONNEES);
        assertThat(testGestionnaire.getNumeroRue()).isEqualTo(UPDATED_NUMERO_RUE);
        assertThat(testGestionnaire.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testGestionnaire.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testGestionnaire.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGestionnaire() throws Exception {
        int databaseSizeBeforeUpdate = gestionnaireRepository.findAll().size();

        // Create the Gestionnaire

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestionnaireMockMvc.perform(put("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireService.save(gestionnaire);

        int databaseSizeBeforeDelete = gestionnaireRepository.findAll().size();

        // Delete the gestionnaire
        restGestionnaireMockMvc.perform(delete("/api/gestionnaires/{id}", gestionnaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
