package com.SpringProject.EmployeeManagementSystem.InterviewProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empid;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String role;

    private String designation;

    private long salary;

    @JsonBackReference
    @ManyToOne
    private Organisation organisation;
}
