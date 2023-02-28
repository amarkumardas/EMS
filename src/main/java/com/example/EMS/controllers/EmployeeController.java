package com.example.EMS.controllers;

import com.example.EMS.entities.Employee;
import com.example.EMS.exception.CannotAccessId;
import com.example.EMS.repository.IEmployeeRepo;
import com.example.EMS.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//@Controller+@ResponseBody
@RequestMapping("api/employee")
public class EmployeeController {

  @Autowired
  IEmployeeService employeeService;

  @Autowired
  IEmployeeRepo employeeRepo;
  @PreAuthorize("hasAnyAuthority('admin','employee')")
  @PostMapping
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
    return new ResponseEntity<Employee>( employeeService.addEmployee(employee), HttpStatus.CREATED);
  }
  @PreAuthorize("hasAuthority('admin')")
  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployee(){
    return new ResponseEntity< >(employeeService.getAllEmployee(),HttpStatus.OK);
  }
  @PreAuthorize("hasAnyAuthority('admin','employee')")
  @GetMapping("/{id}")
  public ResponseEntity< Employee> getEmployeeById(@PathVariable("id") Integer id) throws CannotAccessId {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();////to get current logged username from   securityContext environment
    Integer userID=employeeRepo.findByEmail(authentication.getName()).get().getEmpId();

    if(userID != id && authentication.getAuthorities().contains(new SimpleGrantedAuthority("employee")))//only employee which is login then he can see its own details but not other details
      throw new CannotAccessId("cannot see other employee details");

    return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
  }
  @PreAuthorize("hasAnyAuthority('admin','employee')")
  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Integer id,@RequestBody Employee employee) throws CannotAccessId {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();////to get current logged username from springboot security environment
    Integer userID=employeeRepo.findByEmail(authentication.getName()).get().getEmpId();

    if(userID != id && authentication.getAuthorities().contains(new SimpleGrantedAuthority("employee")))//only customer which is login then he can see its own details but not other details
      throw new CannotAccessId("cannot update other employee details");

    return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
  }
  @PreAuthorize("hasAuthority('admin')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer id){
    return new ResponseEntity<>(employeeService.deleteEmployee(id),HttpStatus.OK);
  }
}








































/**@RequestBody is an annotation used in Spring Framework for Java to indicate that a method parameter should be bound
 * to the body of the HTTP request. It can be used to extract data from the request body, which can be in a variety of
 * formats such as JSON, XML, or plain text.
In practical terms, when a Spring MVC controller method is annotated with @RequestBody, Spring will automatically map
the request body to the parameter of the annotated method, based on the content type of the request*/