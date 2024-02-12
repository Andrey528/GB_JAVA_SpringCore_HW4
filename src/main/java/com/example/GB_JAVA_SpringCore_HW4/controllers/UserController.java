package com.example.GB_JAVA_SpringCore_HW4.controllers;

import com.example.GB_JAVA_SpringCore_HW4.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(name="/user")
public class UserController {
    @Autowired
    private final UserService userService;
}
