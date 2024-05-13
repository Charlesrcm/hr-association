package com.hrassociation.api.repository;

import org.springframework.data.repository.CrudRepository; // permet de fournir des méthodes pour manipuler l'entité
import org.springframework.stereotype.Repository;

import com.hrassociation.api.model.Employee;

@Repository // indique que la classe est un bean et communique avec une BDD
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
