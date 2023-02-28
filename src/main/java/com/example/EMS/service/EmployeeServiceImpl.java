package com.example.EMS.service;

import com.example.EMS.entities.Employee;
import com.example.EMS.entities.Roles;
import com.example.EMS.repository.IEmployeeRepo;
import com.example.EMS.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepo employeeRepo;
    @Autowired
    private IRoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Employee addEmployee(Employee employee) {
       Roles defaultRoleEmplyee=roleRepo.findById(2).orElseThrow();//getting role form db
        Set<Roles> roleObj=new HashSet<>();
        roleObj.add(defaultRoleEmplyee);
        employee.setRoles(roleObj);//set default role

        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer id) {
        Employee existingEmp=employeeRepo.findById(id).get();
                 existingEmp.setName(employee.getName());
                 existingEmp.setEmail(employee.getEmail());
                 existingEmp.setPassword(this.passwordEncoder.encode(employee.getPassword()));
                 existingEmp.setOrgId(employee.getOrgId());

        return employeeRepo.save(existingEmp);
    }

    @Override
    public String deleteEmployee(Integer id) {
        Employee existingEmp=employeeRepo.findById(id).get();
        existingEmp.getRoles().clear();//first clear role before delete due to foreign key constraint
        employeeRepo.delete(employeeRepo.findById(id).get());
        return "Deleted employee id: "+id;
    }
}
