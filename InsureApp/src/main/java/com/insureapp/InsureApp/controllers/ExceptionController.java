package com.insureapp.InsureApp.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler (Exception error){

        ModelAndView exceptionPage = new ModelAndView();
        exceptionPage.setViewName("exception");
        exceptionPage.addObject("errorMsg", error.getMessage());

        return exceptionPage;
    }

}
