package com.dispenser.medication.service;

import com.dispenser.medication.web.api.model.Medication;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.ArrayList;

public class MedicationRowMapper implements RowMapper<Medication> {

    @Override
    public Medication mapRow(ResultSet rs, int rowNum) throws SQLException {

        Medication medication = new Medication();
        medication.setId(rs.getInt("ID"));
        medication.setName(rs.getString("NAME"));
        medication.setDoseDate(rs.getObject("DOSEDATE", OffsetDateTime.class));
        medication.setNihUrl(rs.getString("NIHURL"));
	String contraindications = rs.getString("CONTRAINDICATIONS");
        medication.setContraindications(new ArrayList<String>(Arrays.asList(contraindications.split(","))));

        return medication;

    }
}
