package com.SpringProject.EmployeeManagementSystem.InterviewProject.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orgid;

    private String name;

    private String branch;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisation")
    private List<Employee> employees;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisation")
    private List<Asset> assets;

}
