package com.insureapp.InsureApp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfile {

    private int userId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number cannot be blank!")
    @Pattern(regexp = "([0-9]{10})", message = "Mobile number must be 10 digits")
    private String phone;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email address is not valid!")
    private String email;

}