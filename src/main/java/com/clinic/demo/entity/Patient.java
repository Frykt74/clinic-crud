package com.clinic.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medical_card")
    private Long idMedicalCard;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column(name = "policy", columnDefinition = "bigint")
    private Long policy;

    @Column(name = "home_address", columnDefinition = "TEXT")
    private String homeAddress;

    @Column(name = "phone_number", columnDefinition = "bigint")
    private Long phoneNumber;

    @Column(name = "social_categories", columnDefinition = "TEXT")
    private String socialCategories;

    @Column(name = "snils", columnDefinition = "bigint")
    private Long snils;
}