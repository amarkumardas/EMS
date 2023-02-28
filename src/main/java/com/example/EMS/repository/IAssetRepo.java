package com.example.EMS.repository;

import com.example.EMS.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssetRepo extends JpaRepository<Asset,Integer> {
}
