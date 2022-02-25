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
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service){
        this.service=service;
    }


    @GetMapping("/person")
    public String showReadPage(Model model){
        List<PersonEntity> personList =service.getAll();
        model.addAttribute("personList", personList);

        return "read_person";
    }

    @GetMapping("/person/create")
    public String showCreatePage(Model model){
        model.addAttribute("person",new PersonEntity());
        model.addAttribute("pageTitle", "Create person");

        return "create_person";
    }

    @GetMapping("/person/update/{id}")
    public String showUpdatePage(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes){
        try {
            PersonEntity person=service.get(id);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Update person (id: "+id+" )");

            return "create_person";
        } catch (PersonNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/person";
        }
    }

    @PostMapping("/person/save")
    public String save(PersonEntity person, RedirectAttributes redirectAttributes){
        service.save(person);
        redirectAttributes.addFlashAttribute("message",
                "The person has been saved successfully");

        return "redirect:/person";
    }

    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message",
                    "The person with id "+id+" has been deleted");
        } catch (PersonNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/person";
    }
}
