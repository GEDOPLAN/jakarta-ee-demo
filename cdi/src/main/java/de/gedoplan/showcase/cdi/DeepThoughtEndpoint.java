package de.gedoplan.showcase.cdi;

import de.gedoplan.showcase.cdi.service.DeepThoughtEJBService;
import de.gedoplan.showcase.cdi.service.DeepThoughtCDIService;

import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.extern.java.Log;

@Log
@Path("answer")
@ApplicationScoped
public class DeepThoughtEndpoint implements Serializable {

  @EJB
  DeepThoughtEJBService deepThoughtEJBService;
  
  @Inject
  DeepThoughtCDIService deepThoughtCDIService;

  @Inject
  ServletContext servletContext;

  @GET
  public Response getAnswer(@QueryParam("type") String type) {
    log.info("Started on " + servletContext.getServerInfo());
    if (type != null && type.equals("cdi")) {
      deepThoughtCDIService.getAnswerToQuestionAboutLifeUniverseAndEverything().thenAccept(log::info);
    } else {
      deepThoughtEJBService.getAnswerToQuestionAboutLifeUniverseAndEverything();
    }
    log.info("End");
    return Response.noContent().header("comeback", "7.500.000 years").build();
  }

}
