package com.clinik.demo.controller;

import com.clinik.demo.entity.MedicalCard;
import com.clinik.demo.service.MedicalCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medical-cards")
public class MedicalCardController {

    private final MedicalCardService medicalCardService;

    @Autowired
    private MedicalCardController(MedicalCardService medicalCardService) {
        this.medicalCardService = medicalCardService;
    }

    @GetMapping("")
    public String showAllMedicalCards(Model model) {
        List<MedicalCard> cards = medicalCardService.findAllCards();
        model.addAttribute("cards", cards);
        return "medical-cards";
    }
}
