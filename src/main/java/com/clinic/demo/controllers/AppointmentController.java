package com.clinic.demo.controllers;

import com.clinic.demo.entity.*;
import com.clinic.demo.service.AppointmentService;
import com.clinic.demo.service.FavorService;
import com.clinic.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final FavorService favorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService, FavorService favorService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.favorService = favorService;
    }

    @GetMapping("")
    public String showAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/new")
    public String showNewAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment-form";
    }

    @PatchMapping("/save")
    public String saveAppointment(Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/add-appointment")
    public String addAppointment(@RequestParam("serviceId") Long serviceId,
                                 @RequestParam("idMedicalCard") Long idMedicalCard,
                                 @RequestParam("date") Date date,
                                 @RequestParam("time") Time time) {

        Favor favor = favorService.findById(serviceId);
        Patient patient = patientService.findById(idMedicalCard);
        Appointment appointment = new Appointment();
        appointment.setEmployee(favor.getDoctor());
        appointment.setFavor(favor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointmentService.saveAppointment(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/time")
    public String showAddAppointmentForm(@RequestParam("serviceId") Long serviceId,
                                         @RequestParam("idMedicalCard") Long idMedicalCard,
                                         Model model) {
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("idMedicalCard", idMedicalCard);
        return "add-appointment";
    }


//    @GetMapping("/add-appointment")
//    public String showAddAppointmentForm(@RequestParam("serviceId") Long serviceId, @RequestParam("idMedicalCard") Long idMedicalCard, Model model) {
//        Favor favor = favorService.findById(serviceId);
//        model.addAttribute("favor", favor);
//        Patient patient = patientService.findById(idMedicalCard);
//        model.addAttribute("patient", patient);
//        return "add-appointment";
//    }
//@GetMapping("/new")
//public String showNewAppointmentForm(@RequestParam("serviceId") Long serviceId,
//                                     @RequestParam("idMedicalCard") Long idMedicalCard,
//                                     Model model) {
//    model.addAttribute("appointment", new Appointment());
//    Favor favor = favorService.findById(serviceId);
//    Patient patient = patientService.findById(idMedicalCard);
//    model.addAttribute("favor", favor);
//    model.addAttribute("patient", patient);
//    return "add-appointment";
}
