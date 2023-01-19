package de.gedoplan.showcase.rest.endpoint;

import de.gedoplan.showcase.rest.repository.CustomerRepository;
import de.gedoplan.showcase.rest.model.Customer;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.extern.java.Log;

import java.util.List;

@Log
@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
  @Inject
  CustomerRepository customerRepository;

  @Inject
  // @Context is deprecated
  HttpHeaders httpHeaders;

  @GET
  public List<Customer> getCustomers() {
    httpHeaders.getRequestHeaders().forEach((key, value) -> {
      log.info(key + ": " + value);
    });
    return customerRepository.getCustomerList();
  }

  @GET
  @Path("{number}")
  public Customer getCustomer(@PathParam("number") Long number) {
    return customerRepository.findCustomer(number).orElseThrow(
        () -> new WebApplicationException(Response
            .status(Response.Status.NOT_FOUND)
            .header("error-message", "Customer " + number + " not found")
            .build())
    );
  }

  @Inject
  // @Context is deprecated
  UriInfo uriInfo;

  @POST
  public Response createCustomer(@Valid Customer customer) {
    customerRepository.addCustomer(customer);
    var location = uriInfo.getAbsolutePathBuilder().path(customer.getNumber().toString()).build();
    return Response.created(location).build();
  }
}
