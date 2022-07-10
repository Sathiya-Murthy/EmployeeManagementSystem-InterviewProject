package com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception;

public class OrganisationIdNotFound extends RuntimeException{
    public OrganisationIdNotFound() {
        super("The given Organisation id was not found");
    }
}
