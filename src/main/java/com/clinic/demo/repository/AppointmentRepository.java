package com.clinic.demo.repository;

import com.clinic.demo.entity.Appointment;
import com.clinic.demo.entity.MedicalCard;
import com.clinic.demo.entity.Patient;
import com.clinic.demo.service.MedicalCardService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepository {

    private final SessionFactory sessionFactory;
    private final MedicalCardService medicalCardService;

    @Autowired
    public AppointmentRepository(SessionFactory sessionFactory, MedicalCardService medicalCardService) {
        this.sessionFactory = sessionFactory;
        this.medicalCardService = medicalCardService;
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
            session.persist(appointment);
            session.getTransaction().commit();
        }
    }

//    public void deleteById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Appointment appointment = session.get(Appointment.class, id);
//            session.remove(appointment);
//            session.getTransaction().commit();
//        }
//    }

//    public void deleteById(Long appointmentId) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            Appointment appointment = session.get(Appointment.class, appointmentId);
//            if (appointment == null) {
//                throw new IllegalArgumentException("Appointment not found with id: " + appointmentId);
//            }
//            MedicalCard medicalCard = medicalCardService.findCardByAppointmentId(appointmentId);
//
//            if (medicalCard != null) {
//                medicalCardService.deleteCardById(medicalCard.getId());
//            }
//
//            session.remove(appointment);
//            session.getTransaction().commit();
//        }
//    }

    public void deleteById(Long appointmentId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Appointment appointment = session.get(Appointment.class, appointmentId);
            if (appointment == null) {
                throw new IllegalArgumentException("Appointment not found with id: " + appointmentId);
            }

            MedicalCard medicalCard = medicalCardService.findCardByAppointmentId(appointmentId);
            if (medicalCard != null) {
                medicalCardService.deleteCardById(medicalCard.getId());
            }

            session.remove(appointment);
            session.getTransaction().commit();
        }
    }


    public List<Appointment> getAppointmentsByMedicalCardId(Long idMedicalCard) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT a FROM Appointment a WHERE a.patient.idMedicalCard = :idMedicalCard";
            return session.createQuery(queryString, Appointment.class)
                    .setParameter("idMedicalCard", idMedicalCard)
                    .getResultList();
        }
    }

    public void editAppointmentDate(Long idAppointment, Date newDate) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Appointment appointment = session.get(Appointment.class, idAppointment);
            appointment.setAppointmentDate(newDate);

            session.persist(appointment);
            tx.commit();
        }
    }

    public void updateAppointmentDateAndTime(Long appointmentId, Date appointmentDate, Time appointmentTime) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            session.persist(appointment);
            session.getTransaction().commit();
        }
    }

    public void updateAppointmentPatient(Long appointmentId, Long idMedicalCard) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            Patient patient = session.get(Patient.class, idMedicalCard);
            appointment.setPatient(patient);
            session.persist(appointment);
            session.getTransaction().commit();
        }
    }
}
