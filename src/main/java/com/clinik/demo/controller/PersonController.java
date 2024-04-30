package com.clinik.demo.controller;

import com.clinik.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.clinik.demo.service.PersonService;

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
//
//    // Показать форму для создания новой персоны
//    @GetMapping("/new")
//    public String showNewPersonForm(Model model) {
//        model.addAttribute("person", new Person());
//        return "new-person";
//    }
//
//    // Сохранить новую персону
//    @PostMapping("/save")
//    public String savePerson(@ModelAttribute Person person) {
//        personService.save(person);
//        return "redirect:/people/";
//    }
//
//    // Показать форму для редактирования персоны
//    @GetMapping("/{id}/edit")
//    public String showEditPersonForm(@PathVariable Long id, Model model) {
//        Person person = personService.findById(id);
//        model.addAttribute("person", person);
//        return "edit-person";
//    }
//
//    // Обновить персону
//    @PostMapping("/{id}/update")
//    public String updatePerson(@PathVariable Long id, @ModelAttribute Person person) {
//        personService.save(person);
//        return "redirect:/people/";
//    }
//
//    // Удалить персону
//    @GetMapping("/{id}/delete")
//    public String deletePerson(@PathVariable Long id) {
//        personService.delete(id);
//        return "redirect:/people/";
//    }
}
