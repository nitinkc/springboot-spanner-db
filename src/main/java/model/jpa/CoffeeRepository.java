package model.jpa;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, UUID> {

}
