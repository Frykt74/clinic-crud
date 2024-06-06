package com.clinic.demo.repository;

import com.clinic.demo.entity.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    private final SessionFactory sessionFactory;

    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public List<Person> findAllPeople() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public void savePerson(Person person) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(person);
            session.getTransaction().commit();
        }
    }

//    public Person findPersonById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.get(Person.class, id);
//        }
//    }
//
//    public void deletePersonById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Person person = session.load(Person.class, id);
//            session.delete(person);
//            session.getTransaction().commit();
//        }
//    }
}
