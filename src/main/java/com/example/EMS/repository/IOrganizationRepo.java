package com.example.EMS.repository;

import com.example.EMS.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationRepo extends JpaRepository<Organization,Integer> {
}
