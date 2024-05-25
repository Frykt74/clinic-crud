package com.clinic.demo.controllers;

import com.clinic.demo.entity.Favor;
import com.clinic.demo.entity.Patient;
import com.clinic.demo.service.FavorService;
import com.clinic.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @GetMapping("/search-for-doctor")
    public String showSearchForm() {
        return "patient-search-for-doctor";
    }

    @PostMapping("/search-for-doctor")
    public String searchForDoctor(@RequestParam String keyword, Model model) {
        List<Patient> patients = patientService.findPatientByLastName(keyword);
        model.addAttribute("patients", patients);
        return "result-patient-search-for-doc";
    }

    //    @GetMapping("/search-for-doctor")
//    public String searchForDoc(@RequestParam("keyword") String keyword, Model model) {
//        List<Patient> patients = patientService.findPatientByLastName(keyword);
//        model.addAttribute("patients", patients);
//        return "patient-search-for-doctor";
//    }
//    @GetMapping("/search-for-doctor")
//    public ModelAndView searchForDoc(@RequestParam String keyword) {
//        List<Patient> patients = patientService.findPatientByLastName(keyword);
//        ModelAndView mav = new ModelAndView("result-patient-search-for-doc");
//        mav.addObject("patients", patients);
//        return mav;
//    }
//    @GetMapping("/search-for-doctor")
//    public ModelAndView searchForDoc(@RequestParam(required = false) String keyword) {
//        List<Patient> patients = new ArrayList<>();
//        if (keyword != null) {
//            patients = patientService.findPatientByLastName(keyword);
//        }
//        ModelAndView mav = new ModelAndView("result-patient-search-for-doc");
//        mav.addObject("patients", patients);
//        return mav;
//    }

//    @GetMapping("/search-for-doctor")
//    public ModelAndView searchForDoc(@RequestParam(required = false) String keyword) {
//        List<Patient> patients = new ArrayList<>();
//        if (keyword != null) {
//            patients = patientService.findPatientByLastName(keyword);
//        }
//        ModelAndView mav = new ModelAndView("patient-search-for-doctor");
//        mav.addObject("patients", patients);
//        return mav;
//    }



    @GetMapping("/add-appointment")
    public String addAppointment(@RequestParam("idMedicalCard") Long idMedicalCard, Model model) {
        Patient patient = patientService.findById(idMedicalCard);
        model.addAttribute("patient", patient);
        return "appointment-service";
    }
}
