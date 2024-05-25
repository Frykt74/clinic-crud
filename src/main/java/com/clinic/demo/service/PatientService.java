package com.clinic.demo.service;

import com.clinic.demo.entity.Patient;
import com.clinic.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findPatientByLastName(String lastName) {
        return patientRepository.findPatientByLastName(lastName);
    }

    public List<Patient> findPatientByIdMedicalCard(Long idMedicalCard) {
        return patientRepository.findPatientsByIdMedicalCard(idMedicalCard);
    }

    public List<Patient> findPatientByFirstName(String firstName) {
        return patientRepository.findPatientByFirstName(firstName);
    }

}
