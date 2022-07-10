package com.SpringProject.EmployeeManagementSystem.InterviewProject.Service;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Asset;

import java.util.List;

public interface AssetService {
    Asset saveAssets(Asset asset,int orgid);
    List<Asset> getAllAssets();
    Asset getAssetsByFloor(int assetid);
    Asset updateAssets(Asset asset, int assetid,int orgid);
    void deleteAssets(int assetid);
}
