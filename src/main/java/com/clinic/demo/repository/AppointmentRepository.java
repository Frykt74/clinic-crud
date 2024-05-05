package com.clinic.demo.repository;

import com.clinic.demo.entity.Appointment;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AppointmentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Appointment> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Appointment.class, id));
        }
    }

    public List<Appointment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
            Root<Appointment> root = criteriaQuery.from(Appointment.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public void save(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(appointment);
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Appointment appointment = session.load(Appointment.class, id);
            session.delete(appointment);
            session.getTransaction().commit();
        }
    }
}
