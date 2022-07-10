package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Asset;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Organisation;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.AssetRepository;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImplementation implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    public AssetServiceImplementation(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset saveAssets(Asset asset,int orgid) {
        Organisation organisation= organisationRepository.findById(orgid).orElseThrow();
        asset.setOrganisation(organisation);
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetsByFloor(int assetid) {
        return assetRepository.findById(assetid).orElseThrow();
    }

    @Override
    public Asset updateAssets(Asset asset, int assetid, int orgid) {
        Asset existingAsset = assetRepository.findById(assetid).orElseThrow();
        Organisation organisation= organisationRepository.findById(orgid).orElseThrow();
        existingAsset.setName(asset.getName());
        existingAsset.setCount(asset.getCount());
        existingAsset.setOrganisation(organisation);
        assetRepository.save(existingAsset);
        return existingAsset;
    }

    @Override
    public void deleteAssets(int assetid) {
        assetRepository.findById(assetid).orElseThrow();
        assetRepository.deleteById(assetid);
    }
}
