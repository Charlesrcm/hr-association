package com.hrassociation.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hrassociation.api.model.Employee;
import com.hrassociation.api.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController // indique à Spring boot que c'est un bean. Indique un retour au format JSON
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * Read - Get all employees
   * 
   * @return - An iterable object of Employee full filled
   */
  @GetMapping("/employees")
  public Iterable<Employee> getEmployees() {
    return employeeService.getEmployees();
  }

  /**
   * Read an employee with is id
   * 
   * @param id
   * @return
   */
  @GetMapping("/employee/{id}")
  public Employee getEmployee(@PathVariable("id") final Long id) {
    Optional<Employee> employee = employeeService.getEmployee(id);
    if (employee.isPresent())
      return employee.get();
    else
      return null;
  }

  /**
   * Create - a new employee
   * 
   * @param employee an object Employee
   * @return
   */
  @PostMapping("/employee")
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

  /**
   * Delete - delete an employee
   * 
   * @param id
   */
  @DeleteMapping("/employee/{id}")
  public void deleteEmployee(@PathVariable("id") final Long id) {
    employeeService.deleteEmployee(id);
  }

  /**
   * Update - update an employee
   * 
   * @param id
   * @param employee
   * @return
   */
  @PutMapping("employee/{id}")
  public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {

    // test pour savoir si l'employé existe en bdd avec l'id
    Optional<Employee> e = employeeService.getEmployee(id);
    if (e.isPresent()) {
      Employee currentEmployee = e.get();

      String firstName = employee.getFirstName();
      if (firstName != null)
        currentEmployee.setFirstName(firstName);

      String lastName = employee.getLastName();
      if (lastName != null)
        currentEmployee.setLastName(lastName);

      String mail = employee.getMail();
      if (mail != null)
        currentEmployee.setMail(mail);

      String password = employee.getPassword();
      if (password != null)
        currentEmployee.setPassword(password);

      employeeService.saveEmployee(currentEmployee);

      return currentEmployee;
    } else
      return null;
  }

}
