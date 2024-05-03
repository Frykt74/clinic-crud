package com.clinik.demo.service;

import com.clinik.demo.entity.Service;
import com.clinik.demo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public List<Service> findAllServices() {
        return serviceRepository.findAll();
    }

    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    public List<Service> findServicesByName(String name) {
        return serviceRepository.findAllByName(name);
    }
}
