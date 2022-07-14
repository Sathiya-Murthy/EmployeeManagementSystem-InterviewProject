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
    @PostMapping("/{orgid}/add")
    public ResponseEntity<?> saveAssets(@RequestBody Asset asset,@PathVariable int orgid){
        if(asset.getName().isEmpty()) {
            return new ResponseEntity<String>("Enter the name of asset", HttpStatus.BAD_REQUEST);
        }
        if(asset.getCount()<=0){
            return new ResponseEntity<String>("The asset count should not be empty and greater than zero", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Asset>(assetService.saveAssets(asset,orgid), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Asset> getAllAssets(){
        return assetService.getAllAssets();
    }

    @GetMapping("/get/{assetid}")
    public ResponseEntity<?> getAssetsByFloor(@PathVariable int assetid) {

        return new ResponseEntity<Asset>(assetService.getAssetsByFloor(assetid), HttpStatus.OK);
    }

    @PutMapping("/{orgid}/update/{assetid}")
    public ResponseEntity<?> updateAssets(@PathVariable int assetid, @RequestBody Asset asset,@PathVariable int orgid){
        if(asset.getName().isEmpty()) {
            return new ResponseEntity<String>("Enter the name of asset", HttpStatus.BAD_REQUEST);
        }
        if(asset.getCount()<=0){
            return new ResponseEntity<String>("The asset count should not be empty and greater than zero", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Asset>(assetService.updateAssets(asset,assetid,orgid),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{assetid}")
    public ResponseEntity<String> deleteAssets(@PathVariable int assetid){
        assetService.deleteAssets(assetid);
        return new ResponseEntity<String>("Asset Data Deleted",HttpStatus.OK);
    }


}