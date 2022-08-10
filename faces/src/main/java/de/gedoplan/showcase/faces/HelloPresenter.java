package de.gedoplan.showcase.faces;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.lifecycle.ClientWindowScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Log
@Named
@ApplicationScoped
public class HelloPresenter implements Serializable {

  public String getHello() {
    return "Hello, Faces world!";
  }

  public boolean getFacesContext() {
    return CDI.current().select(FacesContext.class, Default.Literal.INSTANCE).isResolvable();
  }

  public boolean getExternalContext() {
    return CDI.current().select(ExternalContext.class, Default.Literal.INSTANCE).isResolvable();
  }

}
