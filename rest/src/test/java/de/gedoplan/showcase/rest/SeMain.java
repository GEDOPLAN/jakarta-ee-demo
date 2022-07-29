package de.gedoplan.showcase.rest;

import jakarta.ws.rs.SeBootstrap;

public class SeMain {

  public static void main(String[] args) throws Exception {
    SeBootstrap
      .start(RestApplication.class)
      .thenAccept(instance -> {
        System.out.printf("REST server running at %s%n", instance.configuration().baseUri());
      });

    Thread.currentThread().join();
  }

}
