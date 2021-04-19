package model.jpa;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@RequiredArgsConstructor
public class Coffee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Type(type = "uuid-char")
  private UUID id;

  private String size;

  @ManyToOne
  private Customer customer;
}