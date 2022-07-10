package com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Integer> {
}
