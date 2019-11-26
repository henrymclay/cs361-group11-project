package com.dispenser.medication.web.rest;

import com.dispenser.medication.domain.Medication;
import com.dispenser.medication.repository.MedicationRepository;
import com.dispenser.medication.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dispenser.medication.domain.Medication}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MedicationResource {

    private final Logger log = LoggerFactory.getLogger(MedicationResource.class);

    private static final String ENTITY_NAME = "medication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicationRepository medicationRepository;

    public MedicationResource(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    /**
     * {@code POST  /medications} : Create a new medication.
     *
     * @param medication the medication to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medication, or with status {@code 400 (Bad Request)} if the medication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medications")
    public ResponseEntity<Medication> createMedication(@Valid @RequestBody Medication medication) throws URISyntaxException {
        log.debug("REST request to save Medication : {}", medication);
        if (medication.getId() != null) {
            throw new BadRequestAlertException("A new medication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Medication result = medicationRepository.save(medication);
        return ResponseEntity.created(new URI("/api/medications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medications} : Updates an existing medication.
     *
     * @param medication the medication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medication,
     * or with status {@code 400 (Bad Request)} if the medication is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medications")
    public ResponseEntity<Medication> updateMedication(@Valid @RequestBody Medication medication) throws URISyntaxException {
        log.debug("REST request to update Medication : {}", medication);
        if (medication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Medication result = medicationRepository.save(medication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medication.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medications} : get all the medications.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medications in body.
     */
    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        log.debug("REST request to get all Medications");
        return medicationRepository.findAll();
    }

    /**
     * {@code GET  /medications/:id} : get the "id" medication.
     *
     * @param id the id of the medication to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medication, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medications/{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable Long id) {
        log.debug("REST request to get Medication : {}", id);
        Optional<Medication> medication = medicationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(medication);
    }

    /**
     * {@code DELETE  /medications/:id} : delete the "id" medication.
     *
     * @param id the id of the medication to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        log.debug("REST request to delete Medication : {}", id);
        medicationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
