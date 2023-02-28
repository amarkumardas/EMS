package com.example.EMS.service;

import com.example.EMS.entities.Asset;
import com.example.EMS.repository.IAssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements IAssetService {
    @Autowired
    IAssetRepo assetRepo;
    @Override
    public Asset addAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAllAsset() {
        return assetRepo.findAll();
    }

    @Override
    public Asset getAssetById(Integer id) {
        return assetRepo.findById(id).get();
    }

    @Override
    public Asset updateAsset(Asset asset, Integer id) {
        Asset existingAsset=assetRepo.findById(id).get();
             existingAsset.setAssetName(asset.getAssetName());
             existingAsset.setOrgId(asset.getOrgId());

        return assetRepo.save(existingAsset);
    }

    @Override
    public String deleteAsset(Integer id) {
        assetRepo.delete(assetRepo.findById(id).get());
        return "Deleted Asset id: "+id;
    }
}
