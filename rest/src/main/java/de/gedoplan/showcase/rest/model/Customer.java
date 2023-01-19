package de.gedoplan.showcase.rest.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Customer {
  @NotNull
  private Long number;
  @NotNull
  private String name;
  @Email
  private String email;
}
