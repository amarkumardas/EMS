package com.example.EMS.service;

import com.example.EMS.entities.Organization;

import java.util.List;

public interface IOrganizationService {
    Organization addOrganization(Organization organization);

    List<Organization> getAllOrganization();

    Organization getOrganizationById(Integer id);

    Organization updateOrganization(Organization organization, Integer id);

    String deleteOrganization(Integer id);
}
