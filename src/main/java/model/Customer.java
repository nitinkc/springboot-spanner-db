package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Type(type = "uuid-char")
  private UUID id;

  private String name;

  private String email;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private List<Coffee> coffees = new ArrayList<>();

  // Default constructor for Spring Data JPA.
  Customer() {

  }

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Coffee> getCoffees() {
    return coffees;
  }

  public void setCoffees(List<Coffee> coffees) {
    this.coffees = coffees;
  }
}
