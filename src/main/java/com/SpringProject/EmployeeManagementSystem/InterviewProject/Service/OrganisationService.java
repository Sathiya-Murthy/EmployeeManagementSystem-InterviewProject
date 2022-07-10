package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;

import java.util.List;

public interface OrganisationService {
    Organisation saveOrganisation(Organisation organisation);
    List<Organisation> getAllOrganisation();
    Organisation getOrganisationById(int orgid);
    Organisation updateOrganisation(Organisation organisation,int orgid);
}
