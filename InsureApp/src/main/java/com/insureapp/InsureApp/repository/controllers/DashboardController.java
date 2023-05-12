package com.insureapp.InsureApp.repository.controllers;

import com.insurapp.InsureApp.repository.model.User;
import com.insurapp.InsureApp.repository.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){

       User currentUser = dashboardService.storeUserInfoPerSession(authentication, session);

        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

        return "dashboard.html";

    }
}
