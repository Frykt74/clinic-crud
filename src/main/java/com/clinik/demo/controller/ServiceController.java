package com.clinik.demo.controller;

import com.clinik.demo.entity.Service;
import com.clinik.demo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("")
    public String showAllServices(Model model) {
        List<Service> services = serviceService.findAll();
        model.addAttribute("services", services);
        return "services";
    }
}
