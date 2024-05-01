package com.clinik.demo.service;

import com.clinik.demo.entity.MedicalCard;
import com.clinik.demo.repository.MedicalCardRepository;
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
        return medicalCardRepository.findById(id).orElse(null);
    }

    public List<MedicalCard> findAllCards() {
        return medicalCardRepository.findAll();
    }

    public MedicalCard saveCard(MedicalCard medicalCard) {
        return medicalCardRepository.save(medicalCard);
    }

    public void deleteCardById(Long id) {
        medicalCardRepository.deleteById(id);
    }
}
