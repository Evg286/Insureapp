package com.insureapp.InsureApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "inquiry")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int inquiryId;

    private String status;


    @NotBlank(message = "Please enter your name!")
    @Size(min = 2, message = "A name must consist of minimum 2 characters!")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must consist of digits only")
    private String phone;

    @NotBlank(message = "Please enter your email address!")
    @Email(message = "Please enter your email address!")
    private String email;

    @NotBlank(message = "Subject must not be blank!")
    @Size(min = 5, message = "Subject must be at least 5 chars long!")
    private String subject;

    @NotBlank(message = "Please enter a message!")
    @Size(min = 10, message = "A message must consist of at least 10 characters!")
    private String message;



}
