package com.dispenser.medication.web.api;

import com.dispenser.medication.service.MedicationService;
import com.dispenser.medication.web.api.model.Medication;

import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

@Service
public class MedicationApiDelegateImpl implements MedicationApiDelegate {

  private final NativeWebRequest request;

  public MedicationApiDelegateImpl(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<List<Medication>> getByPatientId(Integer patientId) {
    MedicationService medicationService = new MedicationService();
    return ResponseEntity.ok(medicationService.getByPatientId(patientId));
//      List<String> contraindications = new ArrayList<>();
//      contraindications.add("Ethanol");
//      contraindications.add("Morphine");
//      contraindications.add("Warfarin");
//      Medication medicationOne = new Medication();
//      medicationOne.setContraindications(contraindications);
//      Integer id = 1;
//      OffsetDateTime date = OffsetDateTime.now(ZoneId.systemDefault());
//      medicationOne.setId(id);
//      medicationOne.setPatientId(patient_id);
//      medicationOne.setDoseDate(date);
//      medicationOne.setName("Acetaminophen");
//      medicationOne.setNihUrl("https://druginfo.nlm.nih.gov/drugportal/name/acetaminophen");
//
//      id = 2;
//      Medication medicationTwo = new Medication();
//      medicationOne.setContraindications(contraindications);
//      medicationTwo.setId(id);
//      medicationTwo.setPatientId(patient_id);
//      medicationTwo.setDoseDate(date);
//      medicationTwo.setName("Ibuprofen");
//      medicationTwo.setNihUrl("https://druginfo.nlm.nih.gov/drugportal/name/ibuprofen");
//
//      List<Medication> medicationList = new ArrayList<>();
//      medicationList.add(medicationOne);
//      medicationList.add(medicationTwo);
//      return ResponseEntity.ok(medicationList);
  }
}
