package de.gedoplan.showcase.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("hello")
public class HelloResource {
  @Inject
  GreetingService greetingService;

  @GET
  public Response sayHello() {
    return Response.ok(greetingService.greet()).build();
  }
}
