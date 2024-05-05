package com.clinic.demo.repository;

import com.clinic.demo.entity.Doctor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public DoctorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Doctor findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, id);
        }
    }

    public List<Doctor> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Doctor> criteriaQuery = builder.createQuery(Doctor.class);
            Root<Doctor> root = criteriaQuery.from(Doctor.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public void save(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(doctor);
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Doctor doctor = session.load(Doctor.class, id);
            session.delete(doctor);
            session.getTransaction().commit();
        }
    }
}