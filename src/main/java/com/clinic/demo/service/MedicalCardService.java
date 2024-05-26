package com.clinic.demo.service;

import com.clinic.demo.entity.MedicalCard;
import com.clinic.demo.repository.MedicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalCardService {

    private final MedicalCardRepository medicalCardRepository;

    @Autowired
    public MedicalCardService(MedicalCardRepository medicalCardRepository) {
        this.medicalCardRepository = medicalCardRepository;
    }

    public MedicalCard findCardById(Long id) {
        return medicalCardRepository.findById(id);
    }

    public MedicalCard findCardByAppointmentId(Long appointmentId) {
        return medicalCardRepository.findByAppointmentId(appointmentId);
    }

    public List<MedicalCard> findAllCards() {
        return medicalCardRepository.findAll();
    }

    public void saveCard(MedicalCard medicalCard) {
        medicalCardRepository.save(medicalCard);
    }

    public void deleteCardById(Long id) {
        medicalCardRepository.deleteById(id);
    }
}
