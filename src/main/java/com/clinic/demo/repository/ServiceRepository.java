package com.clinic.demo.repository;

import com.clinic.demo.entity.Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepository {

    private final SessionFactory sessionFactory;

    public ServiceRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Service> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Service.class, id));
        }
    }

    public List<Service> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Service> criteriaQuery = builder.createQuery(Service.class);
            Root<Service> root = criteriaQuery.from(Service.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public Service save(Service service) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
            return service;
        }
    }


    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Service service = session.get(Service.class, id);
            if (service != null) {
                session.delete(service);
            }
            session.getTransaction().commit();
        }
    }

    public List<Service> findAllByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Service> criteriaQuery = builder.createQuery(Service.class);
            Root<Service> root = criteriaQuery.from(Service.class);
            criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
