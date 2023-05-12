package com.insureapp.InsureApp.repository.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class Registration {

    @NotBlank(message = "Please enter your name!")
    @Size(min = 2, message = "A name must consist of minimum 2 characters!")
    private String name;

    @NotBlank(message = "Please enter your email address!")
    @Email(message = "Please enter your email address!")
    private String email;

    @NotBlank(message = "Please enter your phone number!")
    @Pattern (regexp = "[0-9]{10}", message = "Please enter a valid phone number!")
    private String phone;

    @NotBlank(message = "Please enter a password!")
    private String password;




}
