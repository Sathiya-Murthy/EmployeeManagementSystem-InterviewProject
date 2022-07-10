package com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception;

public class EmployeeIdNotFound extends RuntimeException {
    public EmployeeIdNotFound() {
        super("The given Employee id was not found");
    }
}
