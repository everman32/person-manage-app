package by.victory.controller;

import by.victory.entity.PersonEntity;
import by.victory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "person_form";
    }

    @PostMapping("/persons/save")
    public String savePerson(PersonEntity person, RedirectAttributes redirectAddtibutes){
        service.save(person);
        redirectAddtibutes.addFlashAttribute("message",
                "The person has been saved successfully");

        return "redirect:/persons";
    }
}
