package com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);

}
