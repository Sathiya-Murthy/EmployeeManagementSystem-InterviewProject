package com.SpringProject.EmployeeManagementSystem.InterviewProject.Controller;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Asset;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    @PostMapping("/{orgid}/add")
    public ResponseEntity<Asset> saveAssets(@RequestBody Asset asset,@PathVariable int orgid){
        return new ResponseEntity<Asset>(assetService.saveAssets(asset,orgid), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Asset> getAllAssets(){
        return assetService.getAllAssets();
    }

    @GetMapping("/get/{assetid}")
    public ResponseEntity<Asset> getAssetsByFloor(@PathVariable int assetid) {


        return new ResponseEntity<Asset>(assetService.getAssetsByFloor(assetid), HttpStatus.OK);
    }

    @PutMapping("/{orgid}/update/{assetid}")
    public ResponseEntity<Asset> updateAssets(@PathVariable int assetid, @RequestBody Asset asset,@PathVariable int orgid){
        return new ResponseEntity<Asset>(assetService.updateAssets(asset,assetid,orgid),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{assetid}")
    public ResponseEntity<String> deleteAssets(@PathVariable int assetid){
        assetService.deleteAssets(assetid);
        return new ResponseEntity<String>("Asset Data Deleted",HttpStatus.OK);
    }


}