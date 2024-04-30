package com.clinik.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicalCard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person id_person;

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

    public void setPolicy(Long policy) {
        this.policy = policy;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSocialCategories(String socialCategories) {
        this.socialCategories = socialCategories;
    }

    public void setSnils(Long snils) {
        this.snils = snils;
    }

    public Long getIdMedicalCard() {
        return idMedicalCard;
    }


    public Long getPolicy() {
        return policy;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getSocialCategories() {
        return socialCategories;
    }

    public Long getSnils() {
        return snils;
    }
}