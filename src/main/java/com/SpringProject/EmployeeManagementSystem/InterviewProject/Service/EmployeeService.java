package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee, int orgid);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(int empid);
    Employee updateEmployee(Employee employee,int empid,int orgid);
    void deleteEmployee(int empid);
}
