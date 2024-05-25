package com.clinic.demo.controllers;

import com.clinic.demo.entity.Appointment;
import com.clinic.demo.entity.MedicalCard;
import com.clinic.demo.service.AppointmentService;
import com.clinic.demo.service.MedicalCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medical-cards")
public class MedicalCardController {

    private final MedicalCardService medicalCardService;
    private final AppointmentService appointmentService;

    @Autowired
    private MedicalCardController(MedicalCardService medicalCardService, AppointmentService appointmentService) {
        this.medicalCardService = medicalCardService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public String showAllMedicalCards(Model model) {
        List<MedicalCard> cards = medicalCardService.findAllCards();
        model.addAttribute("cards", cards);
        return "medical-cards";
    }

    @GetMapping("/patient")
    public String createMedicalCard(@RequestParam("appointmentId") Long appointmentId, Model model) {
        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
        model.addAttribute("appointment", appointment);
        model.addAttribute("medicalCard", new MedicalCard());
        return "appointment-note";
    }


    @PostMapping("/patient")
    public String saveMedicalCard(@RequestParam("appointmentId") String appointmentId,
                                  @RequestParam("complaint") String complaint,
                                  @RequestParam("examination") String examination,
                                  @RequestParam("certificate") String certificate,
                                  @RequestParam("discharge") String discharge) {
        Long appointmentIdLong = Long.parseLong(appointmentId);
        Appointment appointment = appointmentService.findAppointmentById(appointmentIdLong);
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setAppointment(appointment);
        medicalCard.setComplaint(complaint);
        medicalCard.setExamination(examination);
        medicalCard.setCertificate(certificate);
        medicalCard.setDischarge(discharge);
        medicalCardService.saveCard(medicalCard);
        return "redirect:/patients/search-for-doctor";
    }

//    @PostMapping("/patient/{appointmentId}")
//    public String saveMedicalCard(@RequestParam("appointmentId") Long appointmentId) {
//        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
//        MedicalCard medicalCard = new MedicalCard();
//        medicalCard.setAppointment(appointment);
//        medicalCardService.saveCard(medicalCard);
//        return "redirect:/patients/search-for-doctor";
//    }
}
