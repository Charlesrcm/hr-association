package com.hrassociation.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // annotation Lombok. Cette librairie se charge des setter et getter
@Entity // L'annotation indique que la classe correspond à une table en BDD
@Table(name = "employees") // indique le nom de la table associée
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // indique l'auto-incrément de l'id
  private long id;

  @Column(name = "first_name") // ici on met l'attribut Column car notre variable n'est pas la m^ qu'en table
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String mail; // ici il n'y a pas d'attribut car la varibale est la m^

  private String password;
}
