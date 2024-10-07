package com.week3.assignment1.service;

import com.week3.assignment1.entity.Patient;
import com.week3.assignment1.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {
    private Map<Long, Patient> patientMap = new HashMap<>();
    private Long idCounter = 1L;

    public PatientServiceImpl() {
        patientMap.put(idCounter++, new Patient(1L, "Leo Messi", 36, "Male", "messi@example.com", "Miami", LocalDate.of(1993, 5, 15)));
        patientMap.put(idCounter++, new Patient(2L, "Cristiano Ronaldo", 39, "Male", "ronaldo@example.com", "Saudi", LocalDate.of(1998, 7, 20)));
        patientMap.put(idCounter++, new Patient(3L, "Robert Lewandowski", 35, "Male", "robert@example.com", "Barcelona", LocalDate.of(1983, 1, 10)));
    }

    @Override
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    @Override
    public void addPatient(Patient patient) {
        patient.setId(idCounter++);
        patientMap.put(patient.getId(), patient);
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        if (patientMap.containsKey(id)) {
            patient.setId(id);
            patientMap.put(id, patient);
        }
    }

    @Override
    public void deletePatient(Long id) {
        patientMap.remove(id);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientMap.get(id);
    }

    @Override
    public List<Patient> getPatientsByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patientMap.values()) {
            if (patient.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(patient);
            }
        }
        return result;  // Returns the list of matching patients
    }

}

