package com.example.EMS.service;

import com.example.EMS.entities.Asset;

import java.util.List;

public interface IAssetService {
    Asset addAsset(Asset asset);

    List<Asset> getAllAsset();

    Asset getAssetById(Integer id);

    Asset updateAsset(Asset asset, Integer id);

    String deleteAsset(Integer id);
}
