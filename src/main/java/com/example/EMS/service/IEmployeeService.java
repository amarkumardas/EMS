package com.example.EMS.service;

import com.example.EMS.entities.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(Integer id);

    Employee updateEmployee(Employee employee, Integer id);

    String deleteEmployee(Integer id);
}
