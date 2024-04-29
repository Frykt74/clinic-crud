package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_card")

public class MedicalCard {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_appointment")
    private Appointment id_appointment;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "examination")
    private String examination;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "discharge")
    private String discharge;

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge;
    }
}
