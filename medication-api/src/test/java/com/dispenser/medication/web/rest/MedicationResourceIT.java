package com.dispenser.medication.web.rest;

import com.dispenser.medication.MedicationApp;
import com.dispenser.medication.domain.Medication;
import com.dispenser.medication.repository.MedicationRepository;
import com.dispenser.medication.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.dispenser.medication.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicationResource} REST controller.
 */
@SpringBootTest(classes = MedicationApp.class)
public class MedicationResourceIT {

    private static final Integer DEFAULT_PATIENT_ID = 1;
    private static final Integer UPDATED_PATIENT_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOSE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOSE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NIHURL = "AAAAAAAAAA";
    private static final String UPDATED_NIHURL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRAINDICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_CONTRAINDICATIONS = "BBBBBBBBBB";

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMedicationMockMvc;

    private Medication medication;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedicationResource medicationResource = new MedicationResource(medicationRepository);
        this.restMedicationMockMvc = MockMvcBuilders.standaloneSetup(medicationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medication createEntity(EntityManager em) {
        Medication medication = new Medication()
            .patient_id(DEFAULT_PATIENT_ID)
            .name(DEFAULT_NAME)
            .dose_date(DEFAULT_DOSE_DATE)
            .nihurl(DEFAULT_NIHURL)
            .contraindications(DEFAULT_CONTRAINDICATIONS);
        return medication;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medication createUpdatedEntity(EntityManager em) {
        Medication medication = new Medication()
            .patient_id(UPDATED_PATIENT_ID)
            .name(UPDATED_NAME)
            .dose_date(UPDATED_DOSE_DATE)
            .nihurl(UPDATED_NIHURL)
            .contraindications(UPDATED_CONTRAINDICATIONS);
        return medication;
    }

    @BeforeEach
    public void initTest() {
        medication = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedication() throws Exception {
        int databaseSizeBeforeCreate = medicationRepository.findAll().size();

        // Create the Medication
        restMedicationMockMvc.perform(post("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medication)))
            .andExpect(status().isCreated());

        // Validate the Medication in the database
        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeCreate + 1);
        Medication testMedication = medicationList.get(medicationList.size() - 1);
        assertThat(testMedication.getPatient_id()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testMedication.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedication.getDose_date()).isEqualTo(DEFAULT_DOSE_DATE);
        assertThat(testMedication.getNihurl()).isEqualTo(DEFAULT_NIHURL);
        assertThat(testMedication.getContraindications()).isEqualTo(DEFAULT_CONTRAINDICATIONS);
    }

    @Test
    @Transactional
    public void createMedicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicationRepository.findAll().size();

        // Create the Medication with an existing ID
        medication.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicationMockMvc.perform(post("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medication)))
            .andExpect(status().isBadRequest());

        // Validate the Medication in the database
        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicationRepository.findAll().size();
        // set the field null
        medication.setName(null);

        // Create the Medication, which fails.

        restMedicationMockMvc.perform(post("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medication)))
            .andExpect(status().isBadRequest());

        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDose_dateIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicationRepository.findAll().size();
        // set the field null
        medication.setDose_date(null);

        // Create the Medication, which fails.

        restMedicationMockMvc.perform(post("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medication)))
            .andExpect(status().isBadRequest());

        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedications() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        // Get all the medicationList
        restMedicationMockMvc.perform(get("/api/medications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medication.getId().intValue())))
            .andExpect(jsonPath("$.[*].patient_id").value(hasItem(DEFAULT_PATIENT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].dose_date").value(hasItem(DEFAULT_DOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].nihurl").value(hasItem(DEFAULT_NIHURL)))
            .andExpect(jsonPath("$.[*].contraindications").value(hasItem(DEFAULT_CONTRAINDICATIONS)));
    }
    
    @Test
    @Transactional
    public void getMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        // Get the medication
        restMedicationMockMvc.perform(get("/api/medications/{id}", medication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medication.getId().intValue()))
            .andExpect(jsonPath("$.patient_id").value(DEFAULT_PATIENT_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.dose_date").value(DEFAULT_DOSE_DATE.toString()))
            .andExpect(jsonPath("$.nihurl").value(DEFAULT_NIHURL))
            .andExpect(jsonPath("$.contraindications").value(DEFAULT_CONTRAINDICATIONS));
    }

    @Test
    @Transactional
    public void getNonExistingMedication() throws Exception {
        // Get the medication
        restMedicationMockMvc.perform(get("/api/medications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        int databaseSizeBeforeUpdate = medicationRepository.findAll().size();

        // Update the medication
        Medication updatedMedication = medicationRepository.findById(medication.getId()).get();
        // Disconnect from session so that the updates on updatedMedication are not directly saved in db
        em.detach(updatedMedication);
        updatedMedication
            .patient_id(UPDATED_PATIENT_ID)
            .name(UPDATED_NAME)
            .dose_date(UPDATED_DOSE_DATE)
            .nihurl(UPDATED_NIHURL)
            .contraindications(UPDATED_CONTRAINDICATIONS);

        restMedicationMockMvc.perform(put("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedication)))
            .andExpect(status().isOk());

        // Validate the Medication in the database
        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeUpdate);
        Medication testMedication = medicationList.get(medicationList.size() - 1);
        assertThat(testMedication.getPatient_id()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testMedication.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedication.getDose_date()).isEqualTo(UPDATED_DOSE_DATE);
        assertThat(testMedication.getNihurl()).isEqualTo(UPDATED_NIHURL);
        assertThat(testMedication.getContraindications()).isEqualTo(UPDATED_CONTRAINDICATIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingMedication() throws Exception {
        int databaseSizeBeforeUpdate = medicationRepository.findAll().size();

        // Create the Medication

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicationMockMvc.perform(put("/api/medications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medication)))
            .andExpect(status().isBadRequest());

        // Validate the Medication in the database
        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        int databaseSizeBeforeDelete = medicationRepository.findAll().size();

        // Delete the medication
        restMedicationMockMvc.perform(delete("/api/medications/{id}", medication.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Medication> medicationList = medicationRepository.findAll();
        assertThat(medicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
