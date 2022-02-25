package by.victory.controller;

import by.victory.entity.PersonEntity;
import by.victory.exception.PersonNotFoundException;
import by.victory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/persons")
    public String showPersonList(Model model){
        List<PersonEntity> personList =service.getAll();
        model.addAttribute("personList", personList);

        return "persons";
    }

    @GetMapping("/persons/new")
    public String showNewForm(Model model){
        model.addAttribute("person",new PersonEntity());
        model.addAttribute("pageTitle", "Add new person");
        return "person_form";
    }

    @PostMapping("/persons/save")
    public String savePerson(PersonEntity person, RedirectAttributes redirectAddtibutes){
        service.save(person);
        redirectAddtibutes.addFlashAttribute("message",
                "The person has been saved successfully");

        return "redirect:/persons";
    }

    @GetMapping("persons/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAddtibutes){
        try {
            PersonEntity person=service.get(id);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Update person (id: "+id+" )");

            return "person_form";
        } catch (PersonNotFoundException e) {
            redirectAddtibutes.addFlashAttribute("message", e.getMessage());

            return "redirect:/persons";
        }
    }

    @GetMapping("persons/delete/{id}")
    public String deletePerson(@PathVariable("id") Integer id,
                                 RedirectAttributes redirectAddtibutes){
        try {
            service.delete(id);
            redirectAddtibutes.addFlashAttribute("message",
                    "The person with id "+id+" has been deleted");
        } catch (PersonNotFoundException e) {
            redirectAddtibutes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/persons";
    }
}
