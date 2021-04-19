package model.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@RequiredArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Type(type = "uuid-char")
  private UUID id;

  private String name;

  private String email;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private List<Coffee> coffees = new ArrayList<>();
}
