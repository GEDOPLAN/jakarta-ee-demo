package de.gedoplan.showcase.rest;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
  public String greet() {
    return "Hello from Rest";
  }
}
