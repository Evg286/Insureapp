package com.insureapp.InsureApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "application")
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int applicationId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "plan_id", referencedColumnName = "planId", nullable = false)
    private Plan plan;

    private int userId;
    private String name;
    private String phone;
    private String email;
    private String status;
    private String comments;
}