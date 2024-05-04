package com.clinik.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column(name = "profile")
    private String profile;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "employment_date")
    private Date employmentDate;

    @Column(name = "cabinet")
    private String cabinet;
}