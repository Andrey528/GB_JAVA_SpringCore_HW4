package com.example.GB_JAVA_SpringCore_HW4.controllers.web;

import com.example.GB_JAVA_SpringCore_HW4.services.BurgerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/burgers")
public class BurgerController {
    @Autowired
    private final BurgerService burgerService;

    @GetMapping
    public String getAllBurgers(Model model) {
        model.addAttribute("burgers", burgerService.getAllBurgers());
        return "burgers";
    }
}
