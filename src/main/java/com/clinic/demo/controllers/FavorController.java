package com.clinic.demo.controllers;

import com.clinic.demo.entity.Favor;
import com.clinic.demo.service.DoctorService;
import com.clinic.demo.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/favors")
public class FavorController {

    private final FavorService favorService;
    private final DoctorService doctorService;

    @Autowired
    public FavorController(FavorService favorService, DoctorService doctorService) {
        this.favorService = favorService;
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String showAllServices(Model model) {
        List<Favor> favors = favorService.findAllServices();
        model.addAttribute("favors", favors);
        return "services";
    }

    @GetMapping("/new")
    public String showNewService(Model model) {
        Favor favor = new Favor();
        model.addAttribute("service", favor);
        return "service-form";
    }

    @PatchMapping("/save")
    public String saveService(Model model, @ModelAttribute("service") Favor favor) {
        favorService.save(favor);
        return "redirect:/services";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Favor> favors = favorService.findServicesByName(keyword);
        ModelAndView mav = new ModelAndView("search-service");
        mav.addObject("favors", favors);
        return mav;
    }

    @GetMapping("/appointment-favor")
    public String addAppointment(@RequestParam("serviceId") Long serviceId, Model model) {
        Favor favor = favorService.findById(serviceId);
        model.addAttribute("favor", favor);
        return "appointment-service";
    }
}
