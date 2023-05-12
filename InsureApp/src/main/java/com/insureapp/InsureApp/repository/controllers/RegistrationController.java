package com.insureapp.InsureApp.repository.controllers;

import com.insurapp.InsureApp.repository.model.User;
import com.insurapp.InsureApp.repository.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String displayRegistrationPage(Model model) {

        model.addAttribute("user", new User());
        return "registration.html";
    }


    @PostMapping("/addUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, Errors errors) {

        if (errors.hasErrors())
            return "registration.html";

        return userService.registerNewUser(user) ? "redirect:/login?register=true" : "registration.html";
    }


}
