package com.example.EMS.security;

import com.example.EMS.entities.Employee;
import com.example.EMS.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetailsImpl implements UserDetailsService {
    @Autowired
    private IEmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee=this.employeeRepo.findByEmail(username).orElseThrow();
        return employee;
    }
}
