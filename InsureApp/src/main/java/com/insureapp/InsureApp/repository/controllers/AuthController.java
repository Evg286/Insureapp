package com.insureapp.InsureApp.repository.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AuthController {

    private String message;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            @RequestParam(required = false) String register,
            Model model)
    {
        if(error != null)
            message = "Invalid credentials";

        else if(logout != null)
            message = "Logout successful";

        else if(register != null)
            message = "Your account has been registered successfully. Please log in using your credentials.";

        model.addAttribute("message",message);

        return "login.html";
    }

    @GetMapping("/logout")
    public String displayLogoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/login?logout=true";
    }

}
