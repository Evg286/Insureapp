package com.insureapp.InsureApp.repository.controllers;

import com.insurapp.InsureApp.repository.model.UserProfile;
import com.insurapp.InsureApp.repository.service.UserProfileService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/displayProfile")
    public ModelAndView displayUserProfile(HttpSession session) {

        UserProfile userProfile = userProfileService.getUserProfile(session);

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("userProfile") UserProfile userProfile, Errors errors, HttpSession session) {

        if (errors.hasErrors())
            return "profile.html";

        userProfileService.updateUserProfile(userProfile,session);



        return "redirect:/displayProfile";
    }


}
