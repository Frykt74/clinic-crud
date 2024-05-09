package com.clinic.demo.controllers;

import com.clinic.demo.entity.Favor;
import com.clinic.demo.entity.Patient;
import com.clinic.demo.service.FavorService;
import com.clinic.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final FavorService favorService;

    @Autowired
    public PatientController(PatientService patientService, FavorService favorService) {
        this.patientService = patientService;
        this.favorService = favorService;
    }

    @GetMapping("")
    public String showAllPatients(Model model) {
        List<Patient> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                         @RequestParam("serviceId") Long serviceId,
                         Model model) {
        List<Patient> patients = patientService.findPatientByLastName(keyword);
        model.addAttribute("patients", patients);
        Favor favor = favorService.findById(serviceId);
        model.addAttribute("favor", favor);
        return "search-patient";
    }

    @GetMapping("/add-appointment")
    public String addAppointment(@RequestParam("idMedicalCard") Long idMedicalCard, Model model) {
        Patient patient = patientService.findById(idMedicalCard);
        model.addAttribute("patient", patient);
        return "appointment-service";
    }
}
