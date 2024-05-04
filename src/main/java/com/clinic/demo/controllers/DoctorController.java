package com.clinic.demo.controllers;

import com.clinic.demo.entity.Doctor;
import com.clinic.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    private DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String showAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }
}
