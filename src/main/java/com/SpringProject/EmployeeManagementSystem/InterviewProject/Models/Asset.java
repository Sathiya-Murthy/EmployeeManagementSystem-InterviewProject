package com.SpringProject.EmployeeManagementSystem.InterviewProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assetid;

    private String name;

    private Long count;

    @JsonBackReference
    @ManyToOne
    private Organisation organisation;

}
