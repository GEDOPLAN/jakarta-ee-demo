package de.gedoplan.showcase.cdi;

import de.gedoplan.showcase.cdi.service.DeepThoughtService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

import java.io.Serializable;

@Log
@Path("answer")
@ApplicationScoped
public class DeepThoughtEndpoint implements Serializable {

  @Inject
  DeepThoughtService deepThoughtService;

  @GET
  public Response getAnswer(@QueryParam("type") String type) {
    log.info("Start request");
    if (type != null && type.equals("cdi")) {
      deepThoughtService.cdi();
    } else {
      deepThoughtService.ejb();
    }
    log.info("End request");
    return Response.noContent().header("comeback", "7.500.000 years").build();
  }

}
