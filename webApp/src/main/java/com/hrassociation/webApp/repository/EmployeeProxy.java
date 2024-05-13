package com.hrassociation.webApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hrassociation.webApp.CustomProperties;
import com.hrassociation.webApp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

  @Autowired
  private CustomProperties props;

  /**
   * Get all employees
   * 
   * @return an iterable of all employees
   */
  public Iterable<Employee> getEmployees() {

    String baseApiUrl = props.getApiUrl();
    String getEmployeesUrl = baseApiUrl + "/employees";

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Iterable<Employee>> response = restTemplate
        .exchange(getEmployeesUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Employee>>() {
            });

    log.debug("Get employees call " + response.getStatusCode().toString());

    return response.getBody();

  }

  /**
   * Get an employee with is id
   * 
   * @param id
   * @return an employee witch match the id
   */
  public Employee getEmployee(final int id) {

    String baseApiUrl = props.getApiUrl();
    String getEmployeeUrl = baseApiUrl + "/employee/" + id;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Employee> response = restTemplate.exchange(
        getEmployeeUrl,
        HttpMethod.GET,
        null,
        Employee.class);

    log.debug("Get employee call " + response.getStatusCode().toString());

    return response.getBody();
  }

  /**
   * Create an employee
   * 
   * @param e
   * @return
   */
  public Employee createEmployee(Employee e) {

    String baseUrl = props.getApiUrl();
    String createEmployeeUrl = baseUrl + "/employee";

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Employee> request = new HttpEntity<Employee>(e);
    ResponseEntity<Employee> response = restTemplate.exchange(
        createEmployeeUrl,
        HttpMethod.POST,
        request,
        Employee.class);

    log.debug("Create employee call " + response.getStatusCode().toString());

    return response.getBody();
  }

  /**
   * Update an employee
   * 
   * @param e
   * @return
   */
  public Employee updateEmployee(Employee e) {

    String baseUrl = props.getApiUrl();
    String updateEmployeeUrl = baseUrl + "/employee/" + e.getId();

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Employee> request = new HttpEntity<Employee>(e);
    ResponseEntity<Employee> response = restTemplate.exchange(
        updateEmployeeUrl,
        HttpMethod.PUT,
        request,
        Employee.class);

    log.debug("Update employee call " + response.getStatusCode().toString());

    return response.getBody();
  }

  /**
   * Delete an employee witch match the id
   * 
   * @param id
   */
  public void deleteEmployee(int id) {

    String baseApiUrl = props.getApiUrl();
    String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Void> response = restTemplate.exchange(
        deleteEmployeeUrl,
        HttpMethod.DELETE,
        null,
        Void.class);

    log.debug("Delete employee call " + response.getStatusCode().toString());
  }

}
