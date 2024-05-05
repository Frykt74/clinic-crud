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
}
