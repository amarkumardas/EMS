package com.example.EMS.controllers;

import com.example.EMS.entities.Employee;
import com.example.EMS.entities.Organization;
import com.example.EMS.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    IOrganizationService organizationService;
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization){
      return new ResponseEntity<>(organizationService.addOrganization(organization), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganization(){
        return new ResponseEntity< >(organizationService.getAllOrganization(),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(organizationService.getOrganizationById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Organization> updateOrganizationById(@PathVariable("id") Integer id,@RequestBody Organization organization){
        return new ResponseEntity<>(organizationService.updateOrganization(organization,id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteOrganizationById (@PathVariable("id") Integer id){
        return  new ResponseEntity<>(organizationService.deleteOrganization(id),HttpStatus.OK);
    }
}
