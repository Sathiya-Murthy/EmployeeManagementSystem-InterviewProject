package com.SpringProject.EmployeeManagementSystem.InterviewProject.Controller;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Organisation> saveOrganisation(@RequestBody Organisation organisation){
        return new ResponseEntity<Organisation>(organisationService.saveOrganisation(organisation), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Organisation> getOrganisation(){
        return organisationService.getAllOrganisation();
    }

    @GetMapping("/get/{orgid}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable int orgid) {
        return new ResponseEntity<Organisation>(organisationService.getOrganisationById(orgid), HttpStatus.OK);
    }
    @PutMapping("/update/{orgid}")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable int orgid,@RequestBody Organisation organisation){
        return new ResponseEntity<Organisation>(organisationService.updateOrganisation(organisation,orgid),HttpStatus.OK);
    }
}
