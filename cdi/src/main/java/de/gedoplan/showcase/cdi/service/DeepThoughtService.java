package de.gedoplan.showcase.cdi.service;

import jakarta.ejb.EJB;
import jakarta.enterprise.concurrent.Asynchronous;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Log
@ApplicationScoped
public class DeepThoughtService implements Serializable {
  @Inject
  DeepThoughtCDIService deepThoughtCDIService;

  @EJB
  DeepThoughtEJBService deepThoughtEJBService;

  @Asynchronous
  public void cdi() {
    log.info("Thinking ...");
    deepThoughtCDIService.getAnswerToQuestionAboutLifeUniverseAndEverything().thenAccept(log::info);
  }

  @Asynchronous
  public void ejb() {
    var handle = deepThoughtEJBService.getAnswerToQuestionAboutLifeUniverseAndEverything();
    while (true) {
      try {
        String answer = handle.get(1000, TimeUnit.MILLISECONDS);
        log.info(answer);
        break;
      } catch (TimeoutException | ExecutionException | InterruptedException exception) {
        log.info("Thinking ...");
      }
    }
  }
}
