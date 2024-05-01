package com.clinik.demo.repository;

import com.clinik.demo.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
