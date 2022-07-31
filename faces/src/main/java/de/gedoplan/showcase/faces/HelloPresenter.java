package de.gedoplan.showcase.faces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class HelloPresenter {

  public String getHello() {
    return "Hello, Faces world!";
  }
}
