package de.gedoplan.showcase.rest.endpoint;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("hello")
@ApplicationScoped
public class HelloResource {
  @Inject
  @ConfigProperty(name = "greeting.message", defaultValue = "Hello from nowhere")
  private String message;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return message;
  }
  
}
