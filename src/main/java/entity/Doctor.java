package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn // Не указываем имя столбца, Hibernate использует значение по умолчанию
    private Person person;

    @Column(name = "profile")
    private String profile;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "employment_date")
    private Date employmentDate;

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}