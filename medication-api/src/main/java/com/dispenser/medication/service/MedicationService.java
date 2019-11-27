package com.dispenser.medication.service;

import com.dispenser.medication.web.api.model.Medication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.sql.DataSource;
import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class MedicationService {

  @Inject
  @Qualifier("dataSource")
  private DataSource dataSource;
  
  @Autowired
  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

  public List<Medication> getByPatientId(Integer patientId) {
    String sql = "SELECT * FROM medication WHERE patientId = '" + patientId + "'";
    List<Medication> medicationList = jdbcTemplate.query(
        sql,
	      new MedicationRowMapper());
    return medicationList;
  }
}
