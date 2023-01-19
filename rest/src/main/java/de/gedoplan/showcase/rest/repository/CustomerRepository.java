package de.gedoplan.showcase.rest.repository;

import de.gedoplan.showcase.rest.model.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class CustomerRepository {
  private Map<Long, Customer> customerList;

  @PostConstruct
  void init() {
    customerList = new HashMap<>();
  }

  public List<Customer> getCustomerList() {
    return customerList.values().stream().toList();
  }

  public Optional<Customer> findCustomer(Long number) {
    return Optional.ofNullable(customerList.get(number));
  }

  public void addCustomer(Customer customer) {
    customerList.put(customer.getNumber(), customer);
  }
}
