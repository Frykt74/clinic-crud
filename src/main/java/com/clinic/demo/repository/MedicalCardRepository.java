package com.clinic.demo.repository;

import com.clinic.demo.entity.MedicalCard;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalCardRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public MedicalCardRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MedicalCard findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MedicalCard.class, id);
        }
    }

//    public MedicalCard findByAppointmentId(Long appointmentId) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.get(MedicalCard.class, appointmentId);
//        }
//    }

    public List<MedicalCard> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MedicalCard> criteriaQuery = builder.createQuery(MedicalCard.class);
            Root<MedicalCard> root = criteriaQuery.from(MedicalCard.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public MedicalCard save(MedicalCard medicalCard) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id = (Long) session.save(medicalCard);
            session.getTransaction().commit();
            medicalCard.setId(id);
            return medicalCard;
        }
    }

//    public void deleteById(Long medicalCardId) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            MedicalCard medicalCard = session.get(MedicalCard.class, medicalCardId);
//            session.remove(medicalCard);
//            session.getTransaction().commit();
//        }
//    }

    public MedicalCard findByAppointmentId(Long appointmentId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM MedicalCard mc WHERE mc.appointment.idAppointment = :appointmentId";
            return session.createQuery(hql, MedicalCard.class)
                    .setParameter("appointmentId", appointmentId)
                    .uniqueResult();
        }
    }

    public void deleteById(Long medicalCardId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            MedicalCard medicalCard = session.get(MedicalCard.class, medicalCardId);
            if (medicalCard != null) {
                session.remove(medicalCard);
            }
            session.getTransaction().commit();
        }
    }

}
