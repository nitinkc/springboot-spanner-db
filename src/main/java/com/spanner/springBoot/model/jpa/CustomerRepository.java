package com.spanner.springBoot.model.jpa;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

}
