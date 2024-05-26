package com.clinic.demo.repository;

import com.clinic.demo.entity.Doctor;
import com.clinic.demo.entity.Favor;
import com.clinic.demo.entity.Patient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FavorRepository {

    private final SessionFactory sessionFactory;

    public FavorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Favor> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Favor.class, id));
        }
    }

    public List<Favor> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Favor> criteriaQuery = builder.createQuery(Favor.class);
            Root<Favor> root = criteriaQuery.from(Favor.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public Favor save(Favor favor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(favor);
            session.getTransaction().commit();
            return favor;
        }
    }


    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Favor favor = session.get(Favor.class, id);
            if (favor != null) {
                session.delete(favor);
            }
            session.getTransaction().commit();
        }
    }

    public List<Favor> findAllByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Favor> criteriaQuery = builder.createQuery(Favor.class);
            Root<Favor> root = criteriaQuery.from(Favor.class);
            criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public List<Favor> findFavorByDoctorsLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT s.* " +
                    "FROM service s " +
                    "INNER JOIN doctor d ON s.id_employee = d.id_employee " +
                    "INNER JOIN person p ON d.id_person = p.id_person " +
                    "WHERE p.last_name LIKE :lastName";
            return session.createNativeQuery(queryString, Favor.class)
                    .setParameter("lastName", "%" + lastName + "%")
                    .getResultList();
        }
    }
}
