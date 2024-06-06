package com.clinic.demo.service;

import com.clinic.demo.entity.Appointment;
import com.clinic.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsByMedicalCardId(Long medicalCardId) {
        return appointmentRepository.getAppointmentsByMedicalCardId(medicalCardId);
    }

    public void updateAppointmentDateAndTime(Long appointmentId, Date appointmentDate, Time appointmentTime) {
        appointmentRepository.updateAppointmentDateAndTime(appointmentId, appointmentDate, appointmentTime);
    }

    public void updateAppointmentPatient(Long appointmentId, Long idMedicalCard) {
        appointmentRepository.updateAppointmentPatient(appointmentId, idMedicalCard);
    }

    public List<Appointment> findByDoctor(String doctorLastName) {
        return appointmentRepository.findAppointmentsByDoctorLastName(doctorLastName);
    }

    public List<Appointment> findByPatient(String patientLastName) {
        return appointmentRepository.findAppointmentsByPatientLastName(patientLastName);
    }
}
