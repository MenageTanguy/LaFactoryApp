package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Formateur;
import com.lafactory.app.repository.FormateurRepository;
import com.lafactory.app.service.FormateurService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FormateurResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormateurResourceIT {

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
    private FormateurRepository formateurRepository;

    @Mock
    private FormateurRepository formateurRepositoryMock;

    @Mock
    private FormateurService formateurServiceMock;

    @Autowired
    private FormateurService formateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormateurMockMvc;

    private Formateur formateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateur createEntity(EntityManager em) {
        Formateur formateur = new Formateur()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .coordonnees(DEFAULT_COORDONNEES)
            .numeroRue(DEFAULT_NUMERO_RUE)
            .rue(DEFAULT_RUE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .ville(DEFAULT_VILLE);
        return formateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateur createUpdatedEntity(EntityManager em) {
        Formateur formateur = new Formateur()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);
        return formateur;
    }

    @BeforeEach
    public void initTest() {
        formateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormateur() throws Exception {
        int databaseSizeBeforeCreate = formateurRepository.findAll().size();

        // Create the Formateur
        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateur)))
            .andExpect(status().isCreated());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeCreate + 1);
        Formateur testFormateur = formateurList.get(formateurList.size() - 1);
        assertThat(testFormateur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testFormateur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFormateur.getCoordonnees()).isEqualTo(DEFAULT_COORDONNEES);
        assertThat(testFormateur.getNumeroRue()).isEqualTo(DEFAULT_NUMERO_RUE);
        assertThat(testFormateur.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testFormateur.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testFormateur.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createFormateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formateurRepository.findAll().size();

        // Create the Formateur with an existing ID
        formateur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormateurMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateur)))
            .andExpect(status().isBadRequest());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormateurs() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        // Get all the formateurList
        restFormateurMockMvc.perform(get("/api/formateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].coordonnees").value(hasItem(DEFAULT_COORDONNEES)))
            .andExpect(jsonPath("$.[*].numeroRue").value(hasItem(DEFAULT_NUMERO_RUE)))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFormateursWithEagerRelationshipsIsEnabled() throws Exception {
        when(formateurServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFormateurMockMvc.perform(get("/api/formateurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(formateurServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFormateursWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(formateurServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFormateurMockMvc.perform(get("/api/formateurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(formateurServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFormateur() throws Exception {
        // Initialize the database
        formateurRepository.saveAndFlush(formateur);

        // Get the formateur
        restFormateurMockMvc.perform(get("/api/formateurs/{id}", formateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formateur.getId().intValue()))
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
    public void getNonExistingFormateur() throws Exception {
        // Get the formateur
        restFormateurMockMvc.perform(get("/api/formateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormateur() throws Exception {
        // Initialize the database
        formateurService.save(formateur);

        int databaseSizeBeforeUpdate = formateurRepository.findAll().size();

        // Update the formateur
        Formateur updatedFormateur = formateurRepository.findById(formateur.getId()).get();
        // Disconnect from session so that the updates on updatedFormateur are not directly saved in db
        em.detach(updatedFormateur);
        updatedFormateur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .coordonnees(UPDATED_COORDONNEES)
            .numeroRue(UPDATED_NUMERO_RUE)
            .rue(UPDATED_RUE)
            .codePostal(UPDATED_CODE_POSTAL)
            .ville(UPDATED_VILLE);

        restFormateurMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormateur)))
            .andExpect(status().isOk());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeUpdate);
        Formateur testFormateur = formateurList.get(formateurList.size() - 1);
        assertThat(testFormateur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFormateur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFormateur.getCoordonnees()).isEqualTo(UPDATED_COORDONNEES);
        assertThat(testFormateur.getNumeroRue()).isEqualTo(UPDATED_NUMERO_RUE);
        assertThat(testFormateur.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testFormateur.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testFormateur.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingFormateur() throws Exception {
        int databaseSizeBeforeUpdate = formateurRepository.findAll().size();

        // Create the Formateur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormateurMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateur)))
            .andExpect(status().isBadRequest());

        // Validate the Formateur in the database
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormateur() throws Exception {
        // Initialize the database
        formateurService.save(formateur);

        int databaseSizeBeforeDelete = formateurRepository.findAll().size();

        // Delete the formateur
        restFormateurMockMvc.perform(delete("/api/formateurs/{id}", formateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formateur> formateurList = formateurRepository.findAll();
        assertThat(formateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
