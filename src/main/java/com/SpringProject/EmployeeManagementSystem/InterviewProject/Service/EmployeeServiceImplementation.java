package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.EmployeeIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.OrganisationIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.EmployeeRepository;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrganisationRepository organisationRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee, int orgid) {
        Organisation organisation= organisationRepository.findById(orgid).orElseThrow(()-> new OrganisationIdNotFound());
        employee.setOrganisation(organisation);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int empid) {
        return employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
    }

    @Override
    public Employee updateEmployee(Employee employee, int empid, int orgid) {
        Employee existingEmployee=employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
        Organisation organisation= organisationRepository.findById(orgid).orElseThrow(()-> new OrganisationIdNotFound());

        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setOrganisation(organisation);
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(int empid) {
        employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
        employeeRepository.deleteById(empid);
    }
}
