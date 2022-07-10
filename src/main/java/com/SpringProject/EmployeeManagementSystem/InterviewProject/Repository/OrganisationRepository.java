package com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}
