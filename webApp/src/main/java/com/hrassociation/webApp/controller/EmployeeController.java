package com.hrassociation.webApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrassociation.webApp.model.Employee;
import com.hrassociation.webApp.service.EmployeeService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeService service;

  /**
   * Redirect to the page where we can create an employee
   * 
   * @param model
   * @return
   */
  @GetMapping("/createEmployee")
  public String createEmployee(Model model) {
    Employee e = new Employee();
    model.addAttribute("employee", e);

    return "formNewEmployee";
  }

  /**
   * Create a new employee
   * 
   * @param employee
   * @return
   */
  @PostMapping("/saveEmployee")
  public ModelAndView saveEmployee(@ModelAttribute Employee employee) { // ModelAttribute permet de récupérer les
                                                                        // informations dans le formulaire et construit
                                                                        // l'objet employé
    service.saveEmployee(employee);

    return new ModelAndView("redirect:/");
  }

  /**
   * Read employee to the home page
   * 
   * @param model
   * @return
   */
  @GetMapping("/")
  public String home(Model model) { // En indiquant Model, spring instancie l'objet Model
    Iterable<Employee> listEmployee = service.getEmployees(); // on récupère l'objet itérable Employé

    /**
     * @param String -> Le nom de l'objet que l'on veut donner
     * @param Object -> l'bjet que l'on donne à l'object Model
     */
    model.addAttribute("employees", listEmployee);// On ajoute l'objet Employé dans l'objet modèle

    return "home";
  }

  @GetMapping("updateEmployee/{id}")
  public String updateEmployee(@PathVariable final int id, Model model) {
    Employee employee = service.getEmployee(id);
    model.addAttribute("employee", employee);

    return "formUpdateEmployee";
  }

  /**
   * Delete an employee
   * 
   * @param id
   * @return
   */
  @GetMapping("/deleteEmployee/{id}")
  public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
    service.deleteEmployee(id);
    return new ModelAndView("redirect:/");
  }

}
