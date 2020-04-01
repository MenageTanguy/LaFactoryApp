package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Cursus;
import com.lafactory.app.repository.CursusRepository;
import com.lafactory.app.service.CursusService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CursusResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CursusResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PREREQUIS = "AAAAAAAAAA";
    private static final String UPDATED_PREREQUIS = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIFS = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIFS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENU = "AAAAAAAAAA";
    private static final String UPDATED_CONTENU = "BBBBBBBBBB";

    @Autowired
    private CursusRepository cursusRepository;

    @Mock
    private CursusRepository cursusRepositoryMock;

    @Mock
    private CursusService cursusServiceMock;

    @Autowired
    private CursusService cursusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCursusMockMvc;

    private Cursus cursus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cursus createEntity(EntityManager em) {
        Cursus cursus = new Cursus()
            .nom(DEFAULT_NOM)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .prerequis(DEFAULT_PREREQUIS)
            .objectifs(DEFAULT_OBJECTIFS)
            .contenu(DEFAULT_CONTENU);
        return cursus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cursus createUpdatedEntity(EntityManager em) {
        Cursus cursus = new Cursus()
            .nom(UPDATED_NOM)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .prerequis(UPDATED_PREREQUIS)
            .objectifs(UPDATED_OBJECTIFS)
            .contenu(UPDATED_CONTENU);
        return cursus;
    }

    @BeforeEach
    public void initTest() {
        cursus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCursus() throws Exception {
        int databaseSizeBeforeCreate = cursusRepository.findAll().size();

        // Create the Cursus
        restCursusMockMvc.perform(post("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursus)))
            .andExpect(status().isCreated());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeCreate + 1);
        Cursus testCursus = cursusList.get(cursusList.size() - 1);
        assertThat(testCursus.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testCursus.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testCursus.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testCursus.getPrerequis()).isEqualTo(DEFAULT_PREREQUIS);
        assertThat(testCursus.getObjectifs()).isEqualTo(DEFAULT_OBJECTIFS);
        assertThat(testCursus.getContenu()).isEqualTo(DEFAULT_CONTENU);
    }

    @Test
    @Transactional
    public void createCursusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cursusRepository.findAll().size();

        // Create the Cursus with an existing ID
        cursus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCursusMockMvc.perform(post("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursus)))
            .andExpect(status().isBadRequest());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCursuses() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        // Get all the cursusList
        restCursusMockMvc.perform(get("/api/cursuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cursus.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].prerequis").value(hasItem(DEFAULT_PREREQUIS)))
            .andExpect(jsonPath("$.[*].objectifs").value(hasItem(DEFAULT_OBJECTIFS)))
            .andExpect(jsonPath("$.[*].contenu").value(hasItem(DEFAULT_CONTENU)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCursusesWithEagerRelationshipsIsEnabled() throws Exception {
        when(cursusServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCursusMockMvc.perform(get("/api/cursuses?eagerload=true"))
            .andExpect(status().isOk());

        verify(cursusServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCursusesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(cursusServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCursusMockMvc.perform(get("/api/cursuses?eagerload=true"))
            .andExpect(status().isOk());

        verify(cursusServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCursus() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        // Get the cursus
        restCursusMockMvc.perform(get("/api/cursuses/{id}", cursus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cursus.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.prerequis").value(DEFAULT_PREREQUIS))
            .andExpect(jsonPath("$.objectifs").value(DEFAULT_OBJECTIFS))
            .andExpect(jsonPath("$.contenu").value(DEFAULT_CONTENU));
    }

    @Test
    @Transactional
    public void getNonExistingCursus() throws Exception {
        // Get the cursus
        restCursusMockMvc.perform(get("/api/cursuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCursus() throws Exception {
        // Initialize the database
        cursusService.save(cursus);

        int databaseSizeBeforeUpdate = cursusRepository.findAll().size();

        // Update the cursus
        Cursus updatedCursus = cursusRepository.findById(cursus.getId()).get();
        // Disconnect from session so that the updates on updatedCursus are not directly saved in db
        em.detach(updatedCursus);
        updatedCursus
            .nom(UPDATED_NOM)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .prerequis(UPDATED_PREREQUIS)
            .objectifs(UPDATED_OBJECTIFS)
            .contenu(UPDATED_CONTENU);

        restCursusMockMvc.perform(put("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCursus)))
            .andExpect(status().isOk());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeUpdate);
        Cursus testCursus = cursusList.get(cursusList.size() - 1);
        assertThat(testCursus.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testCursus.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testCursus.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testCursus.getPrerequis()).isEqualTo(UPDATED_PREREQUIS);
        assertThat(testCursus.getObjectifs()).isEqualTo(UPDATED_OBJECTIFS);
        assertThat(testCursus.getContenu()).isEqualTo(UPDATED_CONTENU);
    }

    @Test
    @Transactional
    public void updateNonExistingCursus() throws Exception {
        int databaseSizeBeforeUpdate = cursusRepository.findAll().size();

        // Create the Cursus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCursusMockMvc.perform(put("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursus)))
            .andExpect(status().isBadRequest());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCursus() throws Exception {
        // Initialize the database
        cursusService.save(cursus);

        int databaseSizeBeforeDelete = cursusRepository.findAll().size();

        // Delete the cursus
        restCursusMockMvc.perform(delete("/api/cursuses/{id}", cursus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
