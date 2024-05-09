package com.clinic.demo.controllers;

import com.clinic.demo.entity.Doctor;
import com.clinic.demo.entity.Service;
import com.clinic.demo.service.DoctorService;
import com.clinic.demo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;
    private final DoctorService doctorService;

    @Autowired
    public ServiceController(ServiceService serviceService, DoctorService doctorService) {
        this.serviceService = serviceService;
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String showAllServices(Model model) {
        List<Service> services = serviceService.findAllServices();
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/new")
    public String showNewService(Model model) {
        Service service = new Service();
        model.addAttribute("service", service);
        return "service-form";
    }

    @PatchMapping("/save")
    public String saveService(Model model, @ModelAttribute("service") Service service) {
        serviceService.save(service);
        return "redirect:/services";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Service> services = serviceService.findServicesByName(keyword);
        ModelAndView mav = new ModelAndView("search-service");
        mav.addObject("services", services);
        return mav;
    }

    @GetMapping("/appointment-service")
    public String appointmentService(@RequestParam("serviceId") Long serviceId, Model model) {
        Service service = serviceService.findById(serviceId);
        List<Doctor> doctors = doctorService.findAllDoctorsByService(service);
        model.addAttribute("doctors", doctors);
        model.addAttribute("service", service);
        return "appointment-service";
    }
}
