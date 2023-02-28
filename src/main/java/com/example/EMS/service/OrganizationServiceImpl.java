package com.example.EMS.service;

import com.example.EMS.entities.Employee;
import com.example.EMS.entities.Organization;
import com.example.EMS.repository.IOrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    IOrganizationRepo organizationRepo;


    @Override
    public Organization addOrganization(Organization organization) {
        return organizationRepo.save(organization);
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepo.findAll();
    }

    @Override
    public Organization getOrganizationById(Integer id) {
        return organizationRepo.findById(id).get();
    }

    @Override
    public Organization updateOrganization(Organization organization, Integer id) {
        Organization existingOrganization=organizationRepo.findById(id).get();
                     existingOrganization.setOrgName(organization.getOrgName());

        return organizationRepo.save(existingOrganization);
    }

    @Override
    public String deleteOrganization(Integer id) {
        Organization org=organizationRepo.findById(id).get();
        List<Employee> emp=org.getEmployee();
        for (Employee employee:emp) {
            employee.getRoles().clear();//while deleting org employee is also deleted so emp roles should also be deleted manually so that table form due to use of @ManyToMany will also be deleted automatically
        }
        organizationRepo.deleteById(id);
        return "Deleted organization id: "+id;
    }
}
