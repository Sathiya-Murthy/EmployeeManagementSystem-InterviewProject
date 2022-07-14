package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;

public interface UpdateEmployeeService {
    Employee updateEmployeeByEmployee(Employee employee, int empid, int orgid);
}
