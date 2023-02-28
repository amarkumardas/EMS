package com.example.EMS.controllers;

import com.example.EMS.entities.Asset;
import com.example.EMS.entities.Employee;
import com.example.EMS.service.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//@Controller+@ResponseBody
@RequestMapping("api/asset")
public class AssetController {
    @Autowired
    private IAssetService assetService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<Asset> saveAsset(@RequestBody Asset asset){
        return new ResponseEntity<Asset>( assetService.addAsset(asset),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping
    public ResponseEntity<List<Asset>> getAllEAsset(){
        return new ResponseEntity<>(assetService.getAllAsset(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(assetService.getAssetById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsssetById(@PathVariable("id") Integer id,@RequestBody Asset asset){
        return new ResponseEntity<>(assetService.updateAsset(asset,id),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssetById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(assetService.deleteAsset(id),HttpStatus.OK);
    }
}
