package com.clinik.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_card")

public class MedicalCard {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_appointment")
    private Appointment appointment;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "examination")
    private String examination;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "discharge")
    private String discharge;
}
