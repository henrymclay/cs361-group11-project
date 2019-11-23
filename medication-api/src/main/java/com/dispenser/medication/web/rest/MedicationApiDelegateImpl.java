package com.dispenser.medication.web.api;

import com.dispenser.medication.web.api.model.Medication;

import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;

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
  public ResponseEntity<List<Medication>> findMedsByName(String name) {
      List<String> contraindications = new ArrayList<>();
      Medication medication = new Medication();
      medication.setContraindications(contraindications);
      Long id = 0L;
      medication.setId(id);
      medication.setName("acetaminophen");
      medication.setNihUrl("https://druginfo.nlm.nih.gov/drugportal/name/acetaminophen");
      medication.setStatus(Medication.StatusEnum.AVAILABLE);
      List<Medication> medicationList = new ArrayList<>();
      medicationList.add(medication);
      return ResponseEntity.ok(medicationList);
  }
}
