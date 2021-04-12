package com.spanner.springBoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import model.Coffee;
import model.CoffeeRepository;
import model.Customer;
import model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web Controller for the sample Hibernate application.
 */
@Controller
public class WebController {

  @Autowired
  private CoffeeRepository coffeeRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping("/")
  public ModelAndView listCustomers() {
    return new ModelAndView("index", getModelMap());
  }

  /**
   * Provides the POST endpoint for creating a customer.
   */
  @PostMapping("/createCustomer")
  public ModelAndView createCustomer(
      @RequestParam("name") String name, @RequestParam("email") String email) {

    Customer customer = new Customer(name, email);
    customerRepository.save(customer);

    Map<String, Object> modelMap = getModelMap();
    modelMap.put("message", "Successfully created customer!");

    return new ModelAndView("index", modelMap);
  }

  /**
   * Provides the POST endpoint for ordering coffees for a customer.
   */
  @PostMapping("/orderCoffee")
  public ModelAndView orderCoffee(
      @RequestParam("customerId") String customerId,
      @RequestParam("size") String size,
      @RequestParam("coffeeCount") int coffeeCount) {

    Customer currentCustomer = customerRepository.findById(UUID.fromString(customerId)).get();

    List<Coffee> coffeeList = new ArrayList<>();
    for (int i = 0; i < coffeeCount; i++) {
      coffeeList.add(new Coffee(currentCustomer, size));
    }
    coffeeRepository.saveAll(coffeeList);

    currentCustomer.getCoffees().addAll(coffeeList);

    customerRepository.saveAll(Arrays.asList(currentCustomer));

    Map<String, Object> modelMap = getModelMap();
    modelMap.put(
        "message",
        "Successfully created " + coffeeCount
            + " coffees for customer " + currentCustomer.getName());

    return new ModelAndView("index", modelMap);
  }

  /**
   * Provides the POST endpoint for deleting a customer and their corresponding records.
   */
  @PostMapping("/deleteCustomer")
  public ModelAndView orderCoffee(@RequestParam("customerId") String customerId) {
    Customer currentCustomer = customerRepository.findById(UUID.fromString(customerId)).get();
    customerRepository.delete(currentCustomer);

    Map<String, Object> modelMap = getModelMap();
    modelMap.put(
        "message",
        "Successfully deleted customer " + currentCustomer.getName() + " and associated orders.");

    return new ModelAndView("index", modelMap);
  }

  private Map<String, Object> getModelMap() {
    HashMap<String, Object> modelMap = new HashMap<>();
    modelMap.put("customers", customerRepository.findAll());
    return modelMap;
  }
}
