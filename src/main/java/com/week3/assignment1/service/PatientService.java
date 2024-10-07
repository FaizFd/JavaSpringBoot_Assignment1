package com.week3.assignment1.service;

import com.week3.assignment1.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    void addPatient(Patient patient);
    void updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    Patient getPatientById(Long id);
    List<Patient> getPatientsByName(String name);  // Add this line
}
