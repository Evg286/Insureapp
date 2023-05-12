package com.insureapp.InsureApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int userId;

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "mobile cannot be blank!")
    @Pattern(regexp = "([0-9]{10})", message = "Phone number must consist of 10 digits")
    private String phone;

    @NotBlank(message = "email confirmation cannot be blank!")
    @Email(message = "Invalid email format!")
    private String email;

    @NotBlank(message = "email confirmation cannot be blank!")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password cannot be blank!")
    private String password;

    @NotBlank(message = "Confirm password cannot be blank!")
    @Transient
    private String confirmPassword;
//
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

}
