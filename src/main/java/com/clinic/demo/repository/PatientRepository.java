package com.clinic.demo.repository;

import com.clinic.demo.entity.Patient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PatientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Patient findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, id);
        }
    }

    public List<Patient> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Patient> criteriaQuery = builder.createQuery(Patient.class);
            Root<Patient> root = criteriaQuery.from(Patient.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public void save(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(patient);
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Patient patient = session.load(Patient.class, id);
            session.delete(patient);
            session.getTransaction().commit();
        }
    }

    public List<Patient> findPatientByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT p.* FROM patient p " +
                    "INNER JOIN person ps ON p.id_person = ps.id_person " +
                    "WHERE ps.last_name LIKE :lastName";
            return session.createNativeQuery(queryString, Patient.class)
                    .setParameter("lastName", "%" + lastName + "%")
                    .getResultList();
        }
    }

    public List<Patient> findPatientsByIdMedicalCard(Long idMedicalCard) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT * FROM patient WHERE id_medical_card = :idMedicalCard";
            return session.createNativeQuery(queryString, Patient.class)
                    .setParameter("idMedicalCard", idMedicalCard)
                    .getResultList();
        }
    }

    public List<Patient> findPatientByFirstName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT p.* FROM patient p " +
                    "INNER JOIN person ps ON p.id_person = ps.id_person " +
                    "WHERE ps.first_name LIKE :firstName";
            return session.createNativeQuery(queryString, Patient.class)
                    .setParameter("firstName", "%" + firstName + "%")
                    .getResultList();
        }
    }

    public List<Patient> findPatientsByPhoneNumber(Long phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT * FROM patient WHERE phone_number = :phoneNumber";
            return session.createNativeQuery(queryString, Patient.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getResultList();
        }
    }
}
