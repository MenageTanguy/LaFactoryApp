package com.lafactory.app.web.rest;

import com.lafactory.app.LaFactoryApp;
import com.lafactory.app.domain.Projecteur;
import com.lafactory.app.repository.ProjecteurRepository;
import com.lafactory.app.service.ProjecteurService;

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
 * Integration tests for the {@link ProjecteurResource} REST controller.
 */
@SpringBootTest(classes = LaFactoryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ProjecteurResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_COUT = 1F;
    private static final Float UPDATED_COUT = 2F;

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    @Autowired
    private ProjecteurRepository projecteurRepository;

    @Autowired
    private ProjecteurService projecteurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjecteurMockMvc;

    private Projecteur projecteur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projecteur createEntity(EntityManager em) {
        Projecteur projecteur = new Projecteur()
            .code(DEFAULT_CODE)
            .cout(DEFAULT_COUT)
            .stock(DEFAULT_STOCK);
        return projecteur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projecteur createUpdatedEntity(EntityManager em) {
        Projecteur projecteur = new Projecteur()
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .stock(UPDATED_STOCK);
        return projecteur;
    }

    @BeforeEach
    public void initTest() {
        projecteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjecteur() throws Exception {
        int databaseSizeBeforeCreate = projecteurRepository.findAll().size();

        // Create the Projecteur
        restProjecteurMockMvc.perform(post("/api/projecteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projecteur)))
            .andExpect(status().isCreated());

        // Validate the Projecteur in the database
        List<Projecteur> projecteurList = projecteurRepository.findAll();
        assertThat(projecteurList).hasSize(databaseSizeBeforeCreate + 1);
        Projecteur testProjecteur = projecteurList.get(projecteurList.size() - 1);
        assertThat(testProjecteur.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProjecteur.getCout()).isEqualTo(DEFAULT_COUT);
        assertThat(testProjecteur.getStock()).isEqualTo(DEFAULT_STOCK);
    }

    @Test
    @Transactional
    public void createProjecteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projecteurRepository.findAll().size();

        // Create the Projecteur with an existing ID
        projecteur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjecteurMockMvc.perform(post("/api/projecteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projecteur)))
            .andExpect(status().isBadRequest());

        // Validate the Projecteur in the database
        List<Projecteur> projecteurList = projecteurRepository.findAll();
        assertThat(projecteurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjecteurs() throws Exception {
        // Initialize the database
        projecteurRepository.saveAndFlush(projecteur);

        // Get all the projecteurList
        restProjecteurMockMvc.perform(get("/api/projecteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projecteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT.doubleValue())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)));
    }
    
    @Test
    @Transactional
    public void getProjecteur() throws Exception {
        // Initialize the database
        projecteurRepository.saveAndFlush(projecteur);

        // Get the projecteur
        restProjecteurMockMvc.perform(get("/api/projecteurs/{id}", projecteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projecteur.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT.doubleValue()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK));
    }

    @Test
    @Transactional
    public void getNonExistingProjecteur() throws Exception {
        // Get the projecteur
        restProjecteurMockMvc.perform(get("/api/projecteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjecteur() throws Exception {
        // Initialize the database
        projecteurService.save(projecteur);

        int databaseSizeBeforeUpdate = projecteurRepository.findAll().size();

        // Update the projecteur
        Projecteur updatedProjecteur = projecteurRepository.findById(projecteur.getId()).get();
        // Disconnect from session so that the updates on updatedProjecteur are not directly saved in db
        em.detach(updatedProjecteur);
        updatedProjecteur
            .code(UPDATED_CODE)
            .cout(UPDATED_COUT)
            .stock(UPDATED_STOCK);

        restProjecteurMockMvc.perform(put("/api/projecteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjecteur)))
            .andExpect(status().isOk());

        // Validate the Projecteur in the database
        List<Projecteur> projecteurList = projecteurRepository.findAll();
        assertThat(projecteurList).hasSize(databaseSizeBeforeUpdate);
        Projecteur testProjecteur = projecteurList.get(projecteurList.size() - 1);
        assertThat(testProjecteur.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProjecteur.getCout()).isEqualTo(UPDATED_COUT);
        assertThat(testProjecteur.getStock()).isEqualTo(UPDATED_STOCK);
    }

    @Test
    @Transactional
    public void updateNonExistingProjecteur() throws Exception {
        int databaseSizeBeforeUpdate = projecteurRepository.findAll().size();

        // Create the Projecteur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjecteurMockMvc.perform(put("/api/projecteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projecteur)))
            .andExpect(status().isBadRequest());

        // Validate the Projecteur in the database
        List<Projecteur> projecteurList = projecteurRepository.findAll();
        assertThat(projecteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjecteur() throws Exception {
        // Initialize the database
        projecteurService.save(projecteur);

        int databaseSizeBeforeDelete = projecteurRepository.findAll().size();

        // Delete the projecteur
        restProjecteurMockMvc.perform(delete("/api/projecteurs/{id}", projecteur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Projecteur> projecteurList = projecteurRepository.findAll();
        assertThat(projecteurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
