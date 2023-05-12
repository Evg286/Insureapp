package com.insureapp.InsureApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PlansController {

    @RequestMapping("/plans")
    public String displayPlansPage(){
        return "plans.html";
    }
}



