package com.clinik.demo.repository;

import com.clinik.demo.entity.Person;
import com.clinik.demo.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByName(String name);
}
