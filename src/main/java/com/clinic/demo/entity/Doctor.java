package com.clinic.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
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