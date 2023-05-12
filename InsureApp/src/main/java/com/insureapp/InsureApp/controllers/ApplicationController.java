package com.insureapp.InsureApp.controllers;

import com.insurapp.InsureApp.model.Application;
import com.insurapp.InsureApp.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/application")
    public String displayApplicationPage(Model model){
        model.addAttribute("apply", new Application());
        return "application.html";
    }

    @PostMapping("/saveApplication")
    public String saveApplication(@Valid @ModelAttribute("apply") Application apply, Errors errors){
        if(errors.hasErrors()){
            log.info("Application form validation failed: " + errors);
            return "application.html";
        }

        applicationService.saveApplicationDetails(apply);

        return "redirect:/application";
    }

    @GetMapping("/applications")
    public ModelAndView displayApplications(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        List<Application> applicationList = applicationService.findApplicationsByStatus();
        modelAndView.setViewName("applications");
        modelAndView.addObject("appList", applicationList);

        return modelAndView;
    }

    @GetMapping("/approveApplication")
    public String approveApplication(@RequestParam int id){

        applicationService.updateApplicationStatus(id);
        return "redirect:/applications";
    }

}
