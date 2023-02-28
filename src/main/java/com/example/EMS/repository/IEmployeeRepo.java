package com.example.EMS.repository;

import com.example.EMS.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmail(String email);
}
