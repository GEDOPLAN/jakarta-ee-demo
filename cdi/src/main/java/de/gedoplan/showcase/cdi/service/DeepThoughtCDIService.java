package de.gedoplan.showcase.cdi.service;

import jakarta.enterprise.concurrent.Asynchronous;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class DeepThoughtCDIService implements Serializable {

  @Asynchronous
  public CompletableFuture<String> getAnswerToQuestionAboutLifeUniverseAndEverything() {
    try {
      // Simulierte Berechnungszeit
      Thread.sleep(7500);
    } catch (InterruptedException e) {
      // ignore
    }
    return Asynchronous.Result.complete("Zweiundvierzig");
  }
  
}
