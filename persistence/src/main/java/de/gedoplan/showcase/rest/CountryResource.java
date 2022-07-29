package de.gedoplan.showcase.rest;

import java.net.URI;
import java.util.List;

import de.gedoplan.showcase.domain.Country;
import de.gedoplan.showcase.persistence.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("countries")
@ApplicationScoped
public class CountryResource {

  @Inject
  CountryRepository countryRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Country> getAll() {
    return this.countryRepository.findAll();
  }

  @Path("{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Country getById(@PathParam("id") String id) {
    return this.countryRepository
        .findById(id)
        .orElseThrow(NotFoundException::new);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public Response post(Country country, @Context UriInfo uriInfo) {
    this.countryRepository.persist(country);

    URI uri = uriInfo.getAbsolutePathBuilder().path(country.getIsoCode()).build();
    return Response.created(uri).build();
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public void put(Country newCountry, @PathParam("id") String id) {
    Country country = getById(id);
    if (!id.equals(country.getIsoCode())) {
      throw new BadRequestException("ids of path and object must match");
    }

    this.countryRepository.merge(newCountry);
  }

  @DELETE
  @Path("{id}")
  @Transactional
  public void delete(@PathParam("id") String id) {
    this.countryRepository.removeById(id);
  }

  @GET
  @Path("byCarCode/{carCode}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Country> getByCarCode(@PathParam("carCode") String carCode) {
    return this.countryRepository.findByCarCode(carCode);
  }

  @GET
  @Path("byPhonePrefix/{phonePrefix}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Country> getByPhonePrefix(@PathParam("phonePrefix") String phonePrefix) {
    return this.countryRepository.findByPhonePrefix(phonePrefix);
  }
}
