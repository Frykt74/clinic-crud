package com.clinic.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
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
    private Time appointmentTime;
}
