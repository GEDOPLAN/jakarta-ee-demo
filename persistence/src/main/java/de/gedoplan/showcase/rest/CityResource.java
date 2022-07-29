package de.gedoplan.showcase.rest;

import java.net.URI;
import java.util.List;

import de.gedoplan.showcase.domain.City;
import de.gedoplan.showcase.persistence.CityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("cities")
@ApplicationScoped
public class CityResource {

  @Inject
  CityRepository cityRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<City> getAll() {
    return this.cityRepository.findAll();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public Response post(City city, @Context UriInfo uriInfo) {
    if (city.getId() != null) {
      throw new BadRequestException("id of new object may not be set upfront");
    }

    this.cityRepository.persist(city);
    this.cityRepository.flush();

    URI uri = uriInfo.getAbsolutePathBuilder().path(city.getId().toString()).build();
    return Response.created(uri).build();
  }

}
