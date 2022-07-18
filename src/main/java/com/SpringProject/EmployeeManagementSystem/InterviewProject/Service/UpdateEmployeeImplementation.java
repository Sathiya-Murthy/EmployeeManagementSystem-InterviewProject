package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.EmployeeIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.OrganisationIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.EmployeeRepository;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeImplementation implements UpdateEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee updateEmployeeByEmployee(Employee employee, int empid, int orgid) {
        Employee existingEmployee=employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
        Organisation organisation= organisationRepository.findById(orgid).orElseThrow(()-> new OrganisationIdNotFound());

          existingEmployee.setFirstname(employee.getFirstname());
          existingEmployee.setLastname(employee.getLastname());
          //existingEmployee.setEmail(employee.getEmail());
          existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
          existingEmployee.setOrganisation(organisation);
          employeeRepository.save(existingEmployee);
          return existingEmployee ;

    }
}
