package com.clinic.demo.service;

import com.clinic.demo.repository.DoctorRepository;
import com.clinic.demo.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }
}
