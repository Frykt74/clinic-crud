package com.clinic.demo.controllers;

import com.clinic.demo.entity.Person;
import com.clinic.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    private PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String showAllPeople(Model model) {
        List<Person> people = personService.findAllPeople();
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/new")
    public String showNewPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") @DateTimeFormat(pattern = "dd.MM.yyyy") Person person) {
        personService.savePerson(person);
        return "redirect:/people";
    }
//
//    Показать форму для редактирования персоны
//    show form for edit person
//    @GetMapping("/{id}/edit")
//    public String showEditPersonForm(@PathVariable Long id, Model model) {
//        Person person = personService.findById(id);
//        model.addAttribute("person", person);
//        return "edit-person";
//    }
//
//    Обновить персону
//    update person
//    @PostMapping("/{id}/update")
//    public String updatePerson(@PathVariable Long id, @ModelAttribute Person person) {
//        personService.saveAppointment(person);
//        return "redirect:/people/";
//    }
//
//    Удалить персону
//    delete person
//    @GetMapping("/{id}/delete")
//    public String deletePersonById(@PathVariable Long id) {
//        personService.delete(id);
//        return "redirect:/people/";
//    }
}
