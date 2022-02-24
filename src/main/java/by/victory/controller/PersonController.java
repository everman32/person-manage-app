package by.victory.controller;

import by.victory.entity.PersonEntity;
import by.victory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
