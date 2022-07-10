package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.EmployeeIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.OrganisationIdNotFound;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImplementation implements OrganisationService{
    @Autowired
    private OrganisationRepository organisationRepository;

    public OrganisationServiceImplementation(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisation() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationById(int orgid) {
        return organisationRepository.findById(orgid).orElseThrow(()-> new OrganisationIdNotFound());
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation, int orgid) {
        Organisation existingOrganisation= organisationRepository.findById(orgid).orElseThrow(()-> new OrganisationIdNotFound());
        existingOrganisation.setName(organisation.getName());
        existingOrganisation.setBranch(organisation.getBranch());

        organisationRepository.save(existingOrganisation);

        return existingOrganisation;
    }
}
