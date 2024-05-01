package com.clinik.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

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

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public Person getPerson() {
        return person;
    }

    public String getProfile() {
        return profile;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
}