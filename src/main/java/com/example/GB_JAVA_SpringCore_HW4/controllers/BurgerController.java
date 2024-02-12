package com.example.GB_JAVA_SpringCore_HW4.controllers;

import com.example.GB_JAVA_SpringCore_HW4.models.Burger;
import com.example.GB_JAVA_SpringCore_HW4.services.BurgerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @GetMapping("/{id}")
//    public String getBurgerById(@PathVariable("id") Long id, Model model) {
//        Burger burger = burgerService.getBurgerById(id);
//        model.addAttribute("burger", burger);
//        return "burger";
//    }
}
