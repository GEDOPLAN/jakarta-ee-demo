package de.gedoplan.showcase.rest.endpoint;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log
@Path("mail")
@RequestScoped
public class MailerResource {
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response sendMail(List<EntityPart> parts) {
    parts.forEach(part -> {
      if (part.getName().equals("content")) {
        extractContent(part).ifPresent(content -> log.info("content: " + content));
      } else {
        log.info(part.getName() + ": " + part.getMediaType());
      }
    });
    return Response.ok().build();
  }

  private Optional<String> extractContent(EntityPart part) {
    try {
      return Optional.ofNullable(part.getContent(String.class));
    } catch (IOException e) {
      log.severe("Error getting content part");
    }
    return Optional.empty();
  }

  @POST
  @Path("direct")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response sendMail(@FormParam("content") String content,
      @FormParam("attachment") byte[] attachment) throws IOException {
    // do something with it
    return Response.ok().build();
  }
}
