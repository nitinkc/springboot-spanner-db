package com.spanner.springBoot.model.jpa;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Coffee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
//  @Type(type = "uuid-char") //Not wprking with Spanner
  private UUID id;

  private String sizes;

  @ManyToOne
  private Customer customer;
}