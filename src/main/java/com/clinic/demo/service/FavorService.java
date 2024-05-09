package com.clinic.demo.service;

import com.clinic.demo.entity.Favor;
import com.clinic.demo.repository.FavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorService {

    private final FavorRepository favorRepository;

    @Autowired
    public FavorService(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    public Favor findById(Long id) {
        return favorRepository.findById(id).orElse(null);
    }

    public List<Favor> findAllServices() {
        return favorRepository.findAll();
    }

    public Favor save(Favor favor) {
        return favorRepository.save(favor);
    }

    public void delete(Long id) {
        favorRepository.deleteById(id);
    }

    public List<Favor> findServicesByName(String name) {
        return favorRepository.findAllByName(name);
    }
}
