package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Ordinateur;
import com.lafactory.app.repository.OrdinateurRepository;
import com.lafactory.app.service.OrdinateurService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrdinateurResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrdinateurResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_COUT = 1F;
    private static final Float UPDATED_COUT = 2F;

    private static final String DEFAULT_PROCESSEUR = "AAAAAAAAAA";
    private static final String UPDATED_PROCESSEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_RAM = 1;
    private static final Integer UPDATED_RAM = 2;

    private static final Integer DEFAULT_DD = 1;
    private static final Integer UPDATED_DD = 2;

    private static final LocalDate DEFAULT_DATE_ACHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ACHAT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    @Autowired
    private OrdinateurRepository ordinateurRepository;

    @Autowired
    private OrdinateurService ordinateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrdinateurMockMvc;

    private Ordinateur ordinateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ordinateur createEntity(EntityManager em) {
        Ordinateur ordinateur = new Ordinateur()
            .code(DEFAULT_CODE)
            .cout(DEFAULT_COUT)
            .processeur(DEFAULT_PROCESSEUR)
            .ram(DEFAULT_RAM)
            .dd(DEFAULT_DD)
            .dateAchat(DEFAULT_DATE_ACHAT)
            .stock(DEFAULT_STOCK);
        return ordinateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ordinateur createUpdatedEntity(EntityManager em) {
        Ordinateur ordinateur = new Ordinateur()
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .processeur(UPDATED_PROCESSEUR)
            .ram(UPDATED_RAM)
            .dd(UPDATED_DD)
            .dateAchat(UPDATED_DATE_ACHAT)
            .stock(UPDATED_STOCK);
        return ordinateur;
    }

    @BeforeEach
    public void initTest() {
        ordinateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdinateur() throws Exception {
        int databaseSizeBeforeCreate = ordinateurRepository.findAll().size();

        // Create the Ordinateur
        restOrdinateurMockMvc.perform(post("/api/ordinateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordinateur)))
            .andExpect(status().isCreated());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurList = ordinateurRepository.findAll();
        assertThat(ordinateurList).hasSize(databaseSizeBeforeCreate + 1);
        Ordinateur testOrdinateur = ordinateurList.get(ordinateurList.size() - 1);
        assertThat(testOrdinateur.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testOrdinateur.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testOrdinateur.getProcesseur()).isEqualTo(DEFAULT_PROCESSEUR);
        assertThat(testOrdinateur.getRam()).isEqualTo(DEFAULT_RAM);
        assertThat(testOrdinateur.getDd()).isEqualTo(DEFAULT_DD);
        assertThat(testOrdinateur.getDateAchat()).isEqualTo(DEFAULT_DATE_ACHAT);
        assertThat(testOrdinateur.getStock()).isEqualTo(DEFAULT_STOCK);
    }

    @Test
    @Transactional
    public void createOrdinateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordinateurRepository.findAll().size();

        // Create the Ordinateur with an existing ID
        ordinateur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdinateurMockMvc.perform(post("/api/ordinateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordinateur)))
            .andExpect(status().isBadRequest());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurList = ordinateurRepository.findAll();
        assertThat(ordinateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrdinateurs() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

        // Get all the ordinateurList
        restOrdinateurMockMvc.perform(get("/api/ordinateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordinateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT.doubleValue())))
            .andExpect(jsonPath("$.[*].processeur").value(hasItem(DEFAULT_PROCESSEUR)))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM)))
            .andExpect(jsonPath("$.[*].dd").value(hasItem(DEFAULT_DD)))
            .andExpect(jsonPath("$.[*].dateAchat").value(hasItem(DEFAULT_DATE_ACHAT.toString())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)));
    }
    
    @Test
    @Transactional
    public void getOrdinateur() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

        // Get the ordinateur
        restOrdinateurMockMvc.perform(get("/api/ordinateurs/{id}", ordinateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordinateur.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT.doubleValue()))
            .andExpect(jsonPath("$.processeur").value(DEFAULT_PROCESSEUR))
            .andExpect(jsonPath("$.ram").value(DEFAULT_RAM))
            .andExpect(jsonPath("$.dd").value(DEFAULT_DD))
            .andExpect(jsonPath("$.dateAchat").value(DEFAULT_DATE_ACHAT.toString()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK));
    }

    @Test
    @Transactional
    public void getNonExistingOrdinateur() throws Exception {
        // Get the ordinateur
        restOrdinateurMockMvc.perform(get("/api/ordinateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdinateur() throws Exception {
        // Initialize the database
        ordinateurService.save(ordinateur);

        int databaseSizeBeforeUpdate = ordinateurRepository.findAll().size();

        // Update the ordinateur
        Ordinateur updatedOrdinateur = ordinateurRepository.findById(ordinateur.getId()).get();
        // Disconnect from session so that the updates on updatedOrdinateur are not directly saved in db
        em.detach(updatedOrdinateur);
        updatedOrdinateur
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .processeur(UPDATED_PROCESSEUR)
            .ram(UPDATED_RAM)
            .dd(UPDATED_DD)
            .dateAchat(UPDATED_DATE_ACHAT)
            .stock(UPDATED_STOCK);

        restOrdinateurMockMvc.perform(put("/api/ordinateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrdinateur)))
            .andExpect(status().isOk());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurList = ordinateurRepository.findAll();
        assertThat(ordinateurList).hasSize(databaseSizeBeforeUpdate);
        Ordinateur testOrdinateur = ordinateurList.get(ordinateurList.size() - 1);
        assertThat(testOrdinateur.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testOrdinateur.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testOrdinateur.getProcesseur()).isEqualTo(UPDATED_PROCESSEUR);
        assertThat(testOrdinateur.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testOrdinateur.getDd()).isEqualTo(UPDATED_DD);
        assertThat(testOrdinateur.getDateAchat()).isEqualTo(UPDATED_DATE_ACHAT);
        assertThat(testOrdinateur.getStock()).isEqualTo(UPDATED_STOCK);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdinateur() throws Exception {
        int databaseSizeBeforeUpdate = ordinateurRepository.findAll().size();

        // Create the Ordinateur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdinateurMockMvc.perform(put("/api/ordinateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordinateur)))
            .andExpect(status().isBadRequest());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurList = ordinateurRepository.findAll();
        assertThat(ordinateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdinateur() throws Exception {
        // Initialize the database
        ordinateurService.save(ordinateur);

        int databaseSizeBeforeDelete = ordinateurRepository.findAll().size();

        // Delete the ordinateur
        restOrdinateurMockMvc.perform(delete("/api/ordinateurs/{id}", ordinateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ordinateur> ordinateurList = ordinateurRepository.findAll();
        assertThat(ordinateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
