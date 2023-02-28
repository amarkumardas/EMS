package com.example.EMS.repository;

import com.example.EMS.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Roles,Integer> {
}
