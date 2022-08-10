package de.gedoplan.showcase.cdi.service;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.concurrent.Future;

@Log
@Stateless
public class DeepThoughtEJBService implements Serializable {
  @Asynchronous
  public Future<String> getAnswerToQuestionAboutLifeUniverseAndEverything() {
    try {
      // Simulierte Berechnungszeit
      Thread.sleep(7500);
    } catch (InterruptedException e) {
      // ignore
    }
    log.info("Got the answer");
    return new AsyncResult<>("Zweiundvierzig");
  }
}
