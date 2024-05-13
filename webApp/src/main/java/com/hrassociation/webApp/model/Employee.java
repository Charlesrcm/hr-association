package com.hrassociation.webApp.model;

import lombok.Data;

@Data // génère les getter et setter
public class Employee {

  private Integer id;

  private String firstName;

  private String lastName;

  private String mail;

  private String password;
}
