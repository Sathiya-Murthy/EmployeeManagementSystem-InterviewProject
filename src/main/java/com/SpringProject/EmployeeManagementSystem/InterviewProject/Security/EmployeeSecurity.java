package com.SpringProject.EmployeeManagementSystem.InterviewProject.Security;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.EmployeeIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.EmployeeUserDetails;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeSecurity implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee =employeeRepository.findByEmail(email);
        employee.orElseThrow(()-> new EmployeeIdNotFound());
        return employee.map(EmployeeUserDetails::new).get();
    }
}