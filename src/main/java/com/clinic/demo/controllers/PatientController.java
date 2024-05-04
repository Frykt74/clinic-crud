package com.clinic.demo.controllers;

import com.clinic.demo.entity.Patient;
import com.clinic.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public String showAllPatients(Model model) {
        List<Patient> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }
}
