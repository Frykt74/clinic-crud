package com.clinik.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppointment;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Doctor employee;

    @ManyToOne
    @JoinColumn(name = "id_medical_card")
    private Patient patient;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    public Long getIdAppointment() {
        return idAppointment;
    }

    public Service getService() {
        return service;
    }

    public Doctor getEmployee() {
        return employee;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
