package com.clinic.demo.repository;

import com.clinic.demo.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
